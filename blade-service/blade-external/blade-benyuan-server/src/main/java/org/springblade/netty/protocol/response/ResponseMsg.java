package org.springblade.netty.protocol.response;


import io.netty.buffer.ByteBuf;

/**
 * 此接口声明了响应消息编码函数，服务端生产的每个响应消息都必须实现此接口。<br>
 * 这里声明的函数在protocolEncoder中会被自动调用，编码成byte stream发送到客户端。
 *
 * @author xxx
 *
 */
public interface ResponseMsg {

	/**
	 * 设置消息号
	 * @param code
	 */
	public void setMsgCode(int code);

	/**
	 * 返回消息的整体封包
	 * @return
	 */
	public ByteBuf entireMsg(ByteBuf out);

	/**
	 * 释放资源(数据流、对象引用)
	 */
	public void release();
}
