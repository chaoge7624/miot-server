package org.springblade.netty.business;

import lombok.Getter;

/**
 * 消息码
 * @author 李家民
 */
@Getter
public enum MsgCode {

	////////////////////// 系统级请求响应 ////////////////////////////
	/** 登陆处理器 */
	CODE_1(1, 0x000002),
	/** 心跳包 无响应 */
	CODE_30(30, 0x000030),

	////////////////////// 业务级请求响应 //////////////////////////
	/** -首页数据- 天气概况 */
	CODE_9911(9911, 0x000012);


	/** 请求码 */
	private Integer req;
	/** 响应码 */
	private Integer resp;

	MsgCode(Integer req_code, Integer resp_code) {
		this.req = req_code;
		this.resp = resp_code;
	}
}
