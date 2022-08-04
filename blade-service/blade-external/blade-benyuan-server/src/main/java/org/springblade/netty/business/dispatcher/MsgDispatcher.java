package org.springblade.netty.business.dispatcher;


import org.springblade.core.tool.api.R;
import org.springblade.netty.business.handle.tag.INotAuthProcessor;
import org.springblade.netty.protocol.GameSession;
import org.springblade.netty.protocol.request.ServerRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * 消息分发器 根据消息号 找到相应的消息处理器
 * @author xxx
 */
public class MsgDispatcher {

	private Map<Integer, MsgProcessor> processorsMap = new HashMap<Integer, MsgProcessor>();

	public MsgDispatcher() {
		for (MsgProcessorRegister register : MsgProcessorRegister.values()) {
			processorsMap.put(register.getMsgCode(), register.getProcessor());
		}
		System.out.println("初始化 消息处理器成功 --- ");
	}

	/**
	 * 通过协议号得到MsgProcessor
	 * @param msgCode
	 * @return
	 */
	public MsgProcessor getMsgProcessor(int msgCode) {
		return processorsMap.get(msgCode);
	}

	/**
	 * 派发消息协议
	 * @param gameSession
	 * @param serverRequest
	 */
	public R dispatchMsg(GameSession gameSession, ServerRequest serverRequest) {
		MsgProcessor processor = getMsgProcessor(serverRequest.getCode());
		try {
			// 是否登录判断 || 存在未实现的接口
			// ...回来补充
			if (true || processor instanceof INotAuthProcessor) {
				return processor.handle(gameSession, serverRequest);
			}
		} catch (NullPointerException e) {
			System.out.println("存在未实现的接口:接口码 = " + serverRequest.getCode());

		}
		return R.fail("存在未实现的接口:接口码 = " + serverRequest.getCode());
	}
}
