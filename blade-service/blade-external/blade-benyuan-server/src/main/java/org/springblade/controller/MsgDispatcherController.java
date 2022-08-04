package org.springblade.controller;

import org.springblade.core.tool.api.R;
import org.springblade.netty.business.dispatcher.MsgDispatcher;
import org.springblade.netty.protocol.GameSession;
import org.springblade.netty.protocol.request.ServerRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 数据转接控制器
 * @author 李家民
 */
@RestController
public class MsgDispatcherController {

	/** 分配器 */
	private static MsgDispatcher msgDispatcher = new MsgDispatcher();

	/**
	 * 消息码请求器
	 * @param serverRequest
	 * @return
	 */
	@PostMapping("/msgCode")
	public R msgCodeRequest(@RequestBody @NotNull ServerRequest serverRequest) {
		return msgDispatcher.dispatchMsg(new GameSession(null, 3), serverRequest);
	}

}
