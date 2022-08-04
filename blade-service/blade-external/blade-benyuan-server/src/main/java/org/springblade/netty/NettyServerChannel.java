package org.springblade.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.springblade.netty.codec.BusinessMsgDecoder;
import org.springblade.netty.codec.BusinessMsgEncoder;

/**
 * 通道初始化器
 */
public class NettyServerChannel extends ChannelInitializer<SocketChannel> {
	public NettyServerChannel() {
		super();
	}

	/**
	 * 在将ChannelHandler添加到实际上下文并准备好处理事件后调用 如果覆盖此方法 请确保您调用 super
	 * @param ctx 通道处理器上下文
	 * @throws Exception
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		super.handlerAdded(ctx);
	}

	/**
	 * Handle the {@link Throwable} by logging and closing the {@link Channel}. Sub-classes may override this.
	 * @param ctx
	 * @param cause
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
	}

	/**
	 * This method will be called once the {@link Channel} was registered. After the method returns this instance
	 * will be removed from the {@link ChannelPipeline} of the {@link Channel}.
	 * @param ch the {@link Channel} which was registered.
	 * @throws Exception is thrown if an error occurs. In that case it will be handled by
	 *                   {@link #exceptionCaught(ChannelHandlerContext, Throwable)} which will by default close
	 *                   the {@link Channel}.
	 */
	@Override
	protected void initChannel(SocketChannel ch) {
		ChannelPipeline pipeline = ch.pipeline();
		// 编解码器 自定义
		pipeline.addLast(new BusinessMsgEncoder());
		pipeline.addLast(new BusinessMsgDecoder());
		pipeline.addLast(new NettyServerInboundHandler());
	}
}
