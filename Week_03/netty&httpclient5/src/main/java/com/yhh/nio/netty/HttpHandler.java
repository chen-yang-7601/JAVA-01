package com.yhh.nio.netty;

import io.netty.channel.ChannelInboundHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;


public class HttpHandler extends ChannelInboundHandlerAdapter{
	
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
    
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		try {
			//logger.info ("channelRead 流量接口请求开始，时间为{}"， startTime);
			FullHttpRequest fullRequest = (FullHttpRequest)msg;
			String uri = fullRequest.uri();
			if (uri.contains("/test")) {
				handlerTest(fullRequest, ctx);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ReferenceCountUtil.release(msg);
		}
	}
	
	private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
		FullHttpResponse response = null;
		try {
			String value = "Hello, Netty server";
			String mao = response.headers().get("mao"); 
			String requestid = response.headers().get("request-id");
			String kk = response.headers().get("kk");
			
			value = value + " mao=" +	mao==null? "":mao 
					      +	" request-id=" + requestid==null?"": requestid 
					      +	" kk=" + kk==null?"": kk;
			System.out.println("value=" + value);
			
			response = new DefaultFullHttpResponse(HTTP_1_1,OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
			response.headers().set("Content-Type", "application/json");
			response.headers().setInt("Content-Length", response.content().readableBytes());
			
		}catch(Exception e) {
			System.out.println("error Handler:" + e.getMessage());
			response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
		}finally {
			if (fullRequest != null) {
				if (!HttpUtil.isKeepAlive(fullRequest)) {
					ctx.write(response).addListener(ChannelFutureListener.CLOSE);
				}else {
					response.headers().set(CONNECTION,KEEP_ALIVE);
					ctx.write(response);
				}
			}
		}
	}
	
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }	
	
}
