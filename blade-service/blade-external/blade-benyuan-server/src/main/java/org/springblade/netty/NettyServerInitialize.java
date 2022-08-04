package org.springblade.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springblade.common.tool.ThreadPoolUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.CompletableFuture;


/**
 * @author 李家民
 */
@Component
public class NettyServerInitialize {



	/** 进程组 */
	private static EventLoopGroup bossGroup;
	private static EventLoopGroup workerGroup;
	private static ServerBootstrap serverBootstrap;
	/** 启动监听 */
	private static ChannelFuture channelFuture;
	/** 端口 */
	private static Integer port = 10222;

	static {
		// Server初始化
		bossGroup = new NioEventLoopGroup(1);
		workerGroup = new NioEventLoopGroup(2);
		serverBootstrap =
			new ServerBootstrap().group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				.childHandler(new NettyServerChannel());
	}

	@PostConstruct
	public void starter() {
		CompletableFuture.runAsync(new Runnable() {
			@Override
			public void run() {
				try {
					channelFuture = serverBootstrap.bind(port)
						.addListener(new ChannelFutureListener() {
							@Override
							public void operationComplete(ChannelFuture future) throws Exception {
								if (future.isSuccess()) {
									System.out.println("监听端口 " + port + " 成功");
								} else {
									System.out.println( "监听端口 " + port + " 失败");
								}
							}
						}).channel().closeFuture().sync();
				} catch (Exception e) {
					e.printStackTrace();
					bossGroup.shutdownGracefully();
					workerGroup.shutdownGracefully();
				}
			}
		}, ThreadPoolUtils.getThreadPool());
	}
}
