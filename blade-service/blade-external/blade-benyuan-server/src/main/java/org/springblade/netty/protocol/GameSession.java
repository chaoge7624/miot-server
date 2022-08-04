package org.springblade.netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import org.springblade.common.constant.NumberConstant;
import org.springblade.netty.protocol.response.ResponseMsg;

/**
 * 消息通道的包装
 * @author 李家民
 */
@Data
public class GameSession {

	/** 通道 */
	private ChannelHandlerContext channelHandlerContext;

	/** 连接类型 1：TCP  2：UDP  3:Web接口 */
	private Integer connectType;

	/**
	 * 构造方法
	 * @param channelHandlerContext 通道
	 */
	public GameSession(ChannelHandlerContext channelHandlerContext, Integer connectType) {
		this.channelHandlerContext = channelHandlerContext;
		this.connectType = connectType;
	}

	/**
	 * 消息发送
	 * @param msg 消息体
	 */
	public void sendMessage(ResponseMsg msg) {
		// 校验连接类型
		if (connectType.equals(NumberConstant.THREE)) {
			return;
		}
		channelHandlerContext.writeAndFlush(msg);
	}

}
