package org.springblade.netty.business.handle.system;

import org.springblade.core.tool.api.R;
import org.springblade.netty.business.dispatcher.MsgProcessor;
import org.springblade.netty.protocol.GameSession;
import org.springblade.netty.protocol.request.ServerRequest;

/**
 * 心跳包
 * @author lijiamin
 */
public class HeartbeatHandler extends MsgProcessor {
	@Override
	public R process(GameSession gameSession, ServerRequest serverRequest) throws Exception {
		// 心跳接收
		// ...
		return null;
	}
}
