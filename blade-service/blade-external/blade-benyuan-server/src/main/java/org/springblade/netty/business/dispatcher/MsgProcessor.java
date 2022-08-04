package org.springblade.netty.business.dispatcher;


import org.springblade.core.tool.api.R;
import org.springblade.netty.protocol.GameSession;
import org.springblade.netty.protocol.request.ServerRequest;
import org.springblade.netty.protocol.response.SendResponse;

/**
 * 策略模式 - 消息处理
 */
public abstract class MsgProcessor {


	public R handle(GameSession gameSession, ServerRequest serverRequest) {
		try {
			return process(gameSession, serverRequest);
		} catch (Exception e) {
			System.out.println("消息处理出错 - msg code:" + serverRequest.getCode());
			e.printStackTrace();
		}
		return R.fail("消息处理出错 - msg code:" + serverRequest.getCode());
	}

	public abstract R process(GameSession gameSession, ServerRequest serverRequest) throws Exception;

	/**
	 * 返回类型接口判断
	 * @param gameSession 连接会话
	 * @param status      消息状态
	 * @param data        实体数据
	 * @param msgCodeResp 消息返回码
	 * @param detail      数据的描述
	 * @return
	 * @throws Exception
	 */
	public R typeJudge(GameSession gameSession, int status, Object data, int msgCodeResp, String detail) throws Exception {
		if (gameSession.getConnectType() == 3) {
			// ...
			return R.data(status, data, "消息返回码=" + Integer.toHexString(msgCodeResp) + " --- " + detail);
		} else {
			// ...
			gameSession.sendMessage(new SendResponse(status, data, msgCodeResp, detail));
		}
		return null;
	}


}
