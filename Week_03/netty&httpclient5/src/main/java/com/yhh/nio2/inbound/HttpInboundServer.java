package com.yhh.nio2.inbound;

import java.util.Arrays;
import java.util.List;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpInboundServer {

	final static Logger logger = LoggerFactory.getLogger(HttpInboundServer.class);
	private int port;
	private List<String> proxyServers;
	
//	public HttpInboundServer(int port) {
//		this.port = port;
//	}
	
	public HttpInboundServer(int port, List<String> proxyServer) {
		this.port = port;
		this.proxyServers = proxyServer;
	}

	
	public void run() throws Exception {
		//主从reactor 模型
		// bossGroup 作为接入的EventLoopGroup,一般一个就足够
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup(16);
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.option(ChannelOption.SO_BACKLOG, 128)
			 .childOption(ChannelOption.TCP_NODELAY, true)
			 .childOption(ChannelOption.SO_KEEPALIVE, true)
			 .childOption(ChannelOption.SO_REUSEADDR,true)
			 .childOption(ChannelOption.SO_RCVBUF, 32*1024)
			 .childOption(ChannelOption.SO_SNDBUF, 32*1024)
			 .childOption(EpollChannelOption.SO_REUSEPORT, true)	 
			 .childOption(ChannelOption.SO_KEEPALIVE, true)
			 .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

			 b.group(bossGroup, workerGroup)
			  .channel(NioServerSocketChannel.class)
			  .handler(new LoggingHandler(LogLevel.INFO))
			  .childHandler(new HttpInboundInitializer(this.proxyServers));
			
			Channel ch = b.bind(port).sync().channel(); 
			
			System.out.println("Open netty HttpServer, listen address and port:http://127.0.01:"+ port);
			ch.closeFuture().sync();
		}finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	
	
//	public static void main(String[] args) throws Exception  {
//		HttpInboundServer nettyServer = new HttpInboundServer(8808);
//		nettyServer.run();
//	}

}
