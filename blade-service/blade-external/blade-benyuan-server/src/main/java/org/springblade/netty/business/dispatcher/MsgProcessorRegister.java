package org.springblade.netty.business.dispatcher;

import lombok.Getter;
import org.springblade.netty.business.MsgCode;
import org.springblade.netty.business.handle.system.HeartbeatHandler;
import org.springblade.netty.business.handle.system.LoginMsgHandler;

/**
 * 消息处理器注册类
 * @author xxx
 */
@Getter
public enum MsgProcessorRegister {

	/** 天气概况 -首页数据 */
	WeatherMsgHandler(MsgCode.CODE_9911.getReq(), null),

	/** 登陆处理器 */
	login(MsgCode.CODE_1.getReq(), new LoginMsgHandler()),
	heart(MsgCode.CODE_30.getReq(), new HeartbeatHandler());

	private int msgCode;
	private MsgProcessor processor;

	/**
	 * 不允许外部创建
	 * @param msgCode
	 * @param processor
	 */
	MsgProcessorRegister(int msgCode, MsgProcessor processor) {
		this.msgCode = msgCode;
		this.processor = processor;
	}
}


