package com.yhh.nio2.outbound.httpclient5;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.DefaultFullHttpResponse;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.hc.client5.http.async.HttpAsyncClient;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequests;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;

import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;

import org.apache.hc.core5.concurrent.FutureCallback;

import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;

import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpRequestInterceptor;

import org.apache.hc.core5.http.protocol.HttpContext;

import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yhh.nio2.router.HttpEndpointRouter;
import com.yhh.nio2.router.RandomHttpEndpointRouter;
import com.yhh.nio2.filter.HttpResponseFilter;
import com.yhh.nio2.filter.HeaderHttpResponseFilter;
import com.yhh.nio2.filter.HttpRequestFilter;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;


public class HttpOutboundHandler {
	
	private CloseableHttpAsyncClient httpClient;
	private ExecutorService proxyService;
	private List<String> backendUrls;

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpOutboundHandler.class);
	HttpEndpointRouter router = new RandomHttpEndpointRouter();
	HttpResponseFilter filter = new HeaderHttpResponseFilter();
	
	public HttpOutboundHandler(List<String> backends) {
		this.backendUrls = backends.stream().map(this::formatUrl).collect(Collectors.toList());
		
		int cores = Runtime.getRuntime().availableProcessors();
		long keepAliveTime = 1_000;
		int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);		
		
		final IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
				.setSoTimeout(Timeout.ofSeconds(5))
				.setIoThreadCount(cores)
				.setRcvBufSize(32 * 1024)
				.build();
		
		httpClient = HttpAsyncClients.custom()
					.setIOReactorConfig(ioReactorConfig)
					// Add a simple request ID to each outgoing request
					.addRequestInterceptorFirst(new HttpRequestInterceptor() {
						private final AtomicLong count = new AtomicLong(0);
						@Override
						public void process(final HttpRequest request, final EntityDetails entity, final HttpContext context)
								throws HttpException, IOException{
							request.setHeader("request-id", Long.toBinaryString(count.incrementAndGet()));
						}
					})
		            .build();
		//start the client
		httpClient.start();
	}
	
    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }
    
	public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx,HttpRequestFilter filter) {
		String backendUrl = router.route(this.backendUrls);
		System.out.println("backendUrl="+ backendUrl);
		final String url= backendUrl+ fullRequest.uri();
		System.out.println("url="+ url);
		filter.filter(fullRequest, ctx);
		proxyService.submit(()->{
			try {
				fetchGet(fullRequest, ctx, url);
			} catch (Exception e) {
				LOGGER.error("handle request failure because of " + e.getMessage(), e);
				e.printStackTrace();
			}
		});
		
	}

	private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url)
		throws Exception{
		
		LOGGER.info("fetchGet, url=" + url);
		
		final SimpleHttpRequest httpget = SimpleHttpRequests.get(url);

		LOGGER.info("Executing request " + httpget.getMethod() + " " + httpget.getUri());
		//System.out.println("Executing request " + httpget.getMethod() + " " + httpget.getUri());
		//httpget.setHeader(HttpConnectionCONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
		httpget.setHeader("mao", inbound.headers().get("mao"));
		httpget.setHeader("request-id", inbound.headers().get("request-id"));
		
		final Future<SimpleHttpResponse> future = httpClient.execute(httpget, new FutureCallback<SimpleHttpResponse>() {
			
			@Override
			public void completed(final SimpleHttpResponse endpointResponse ) {
				try {
					handleResponse(inbound, ctx, endpointResponse);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			@Override 
			public void failed(final Exception ex) {
				System.out.println(url +"--->" + ex);
				LOGGER.info(url +"--->" + ex);
				LOGGER.error("handle request failure because of " + ex.getMessage(), ex);
				ex.printStackTrace();
			}
			
			@Override 
			public void cancelled() {
				//httpGet.abort();
				System.out.println(url +"->" + "cancelled");
				LOGGER.info(url +"->" + "cancelled");
				
			}
			
		});
		future.get();
	}
	
	
	private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, 
			final SimpleHttpResponse endpointResponse) throws Exception{
		FullHttpResponse response = null;
		final int status = HttpStatus.SC_SUCCESS;
		try {
			byte[] body = endpointResponse.getBodyBytes();
			response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
			response.headers().set("Content-Type","application/json");
			response.headers().setInt("Content-Length", Integer.parseInt(endpointResponse.getFirstHeader("Content-Length").getValue()));
			
			for (Header e: endpointResponse.getHeaders()) {
				response.headers().set(e.getName(), e.getValue());
				//Logger
			}
			filter.filter(response);
			
		}catch (Exception e) {
			e.printStackTrace();
			response = response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
			LOGGER.error("handle request failure because of " + e.getMessage(), e);
			exceptionCaught(ctx, e);
		}finally {
			if (fullRequest!=null) {
				if (!HttpUtil.isKeepAlive(fullRequest)) {
					ctx.write(response).addListener(ChannelFutureListener.CLOSE);
				}else {
					//response.headers().set(CONNECTION,KEEP_ALIVE);
					ctx.write(response);
				}
			}
			ctx.flush();
			//ctx.close();
		}
	}
    
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
