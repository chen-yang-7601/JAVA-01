package com.yhh.nio.netty;

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

public class NettyHttpServer {


	private int port;
	public NettyHttpServer(int port) {
		this.port = port;
	}
	
	public void run() throws Exception {
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
			  .childHandler(new HttpInitializer());
			
			Channel ch = b.bind(port).sync().channel(); 
			
			System.out.println("Open netty HttpServer, listen address and port:http://127.0.0.1:"+ port);
			ch.closeFuture().sync();
		}finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	
	
	public static void main(String[] args) throws Exception  {
		NettyHttpServer nettyServer = new NettyHttpServer(8808);
		nettyServer.run();
		
	}

}
