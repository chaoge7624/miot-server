package org.springblade.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.undertow.util.CopyOnWriteMap;
import org.springblade.netty.business.dispatcher.MsgDispatcher;
import org.springblade.netty.protocol.GameSession;
import org.springblade.netty.protocol.request.ServerRequest;
import org.springblade.netty.protocol.response.ResponseMsg;

import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * 处理器
 * @author 李家民
 */
public class NettyServerInboundHandler extends ChannelInboundHandlerAdapter {

	/** 通道储存 */
	private static CopyOnWriteMap<String, Channel> mapChannel = new CopyOnWriteMap<String, Channel>();

	/** 消息分发处理器 */
	private static MsgDispatcher msgDispatcher = new MsgDispatcher();

	/**
	 * 消息广播推送
	 * @param msg 消息体
	 */
	public static void sendBroadcast(ResponseMsg msg) {
		Set<String> setAddress = new HashSet<>();
		// 广播
		mapChannel.forEach(new BiConsumer<String, Channel>() {
			@Override
			public void accept(String address, Channel channel) {
				if (channel.isActive()) {
					channel.writeAndFlush(msg);
				} else {
					setAddress.add(address);
				}
			}
		});
		// 将失活连接铲除
		for (String address : setAddress) {
			mapChannel.remove(address);
		}
	}

	/**
	 * 通道关闭
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		mapChannel.remove(ctx.channel().remoteAddress().toString());
		super.channelInactive(ctx);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		SocketAddress remoteAddress = ctx.channel().remoteAddress();
		System.out.println(remoteAddress.toString() + " ------ 加入群聊！！");
		mapChannel.put(remoteAddress.toString(), ctx.channel());
		super.channelActive(ctx);
	}

	/**
	 * 消息读取
	 * @param ctx
	 * @param msg
	 * @throws Exception
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ServerRequest serverRequest = (ServerRequest) msg;
		msgDispatcher.dispatchMsg(new GameSession(ctx, 1), serverRequest);
	}

	/**
	 * 数据读取完成后的操作
	 * 1. writeAndFlush代表写入+刷新
	 * 2. 还需要对返回的数据进行编码
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
	}

	/**
	 * 发生异常后, 一般是需要关闭通道
	 * @param ctx
	 * @param cause
	 * @throws Exception
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		cause.printStackTrace();
		super.exceptionCaught(ctx, cause);
	}
}
