package org.springblade.netty.protocol.response;


import io.netty.buffer.ByteBuf;
import org.springblade.netty.protocol.MsgProtocol;

/**
 * 服务端发给客户端的消息。 所有返回给客户端的消息都最好继承于它.<br>
 * 这里封装了基本的输出字节操作。
 * @author xxx
 */
public class ServerResponse implements ResponseMsg {
	protected MsgBodyWrap output = MsgBodyWrap.newInstance4Out();
	private int msgCode;
	private int status;

	/** 必须调用此方法设置消息号 */
	public ServerResponse(int status, int msgCode) {
		setStatus(status);
		setMsgCode(msgCode);
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public void setMsgCode(int code) {
		msgCode = code;
	}

	public synchronized ByteBuf entireMsg(ByteBuf out) {
		byte[] body = output.toByteArray();
		// 标志 byte 长度short
		int length = MsgProtocol.flagSize + MsgProtocol.lengthSize + MsgProtocol.msgCodeSize + body.length + 4;
		// 处理
		out.writeByte(MsgProtocol.defaultFlag);
		out.writeInt(length);
		out.writeInt(msgCode);
		out.writeInt(status);
		out.writeByte(0);
		out.writeByte(0);
		out.writeBytes(body);
		return out;
	}

	/**
	 * 释放资源(数据流、对象引用)
	 */
	@Override
	public synchronized void release() {
		if (output != null) {
			output.close();
			output = null;
		}
		output = null;
	}
}
