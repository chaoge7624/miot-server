package org.springblade.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springblade.netty.protocol.response.ResponseMsg;

/**
 * 自定义业务编码器
 * @author 李家民
 */
public class BusinessMsgEncoder extends MessageToByteEncoder {
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		// 引用计数处理 - 多出站处理器会导致资源过度释放
		out.retain();
		ResponseMsg valueMsg = (ResponseMsg) msg;
		valueMsg.entireMsg(out);
		// 资源释放
		valueMsg.release();
	}
}
