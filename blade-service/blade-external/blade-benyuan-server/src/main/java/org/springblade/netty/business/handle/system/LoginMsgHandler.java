package org.springblade.netty.business.handle.system;

import com.alibaba.fastjson.JSONObject;
import org.springblade.core.tool.api.R;
import org.springblade.netty.business.MsgCode;
import org.springblade.netty.business.dispatcher.MsgProcessor;
import org.springblade.netty.business.handle.tag.INotAuthProcessor;
import org.springblade.netty.protocol.GameSession;
import org.springblade.netty.protocol.request.ServerRequest;
import org.springblade.system.user.cache.UserCache;

import java.util.Map;

/**
 * 登录处理器
 * @author lijiamin
 */
public class LoginMsgHandler extends MsgProcessor implements INotAuthProcessor {
	@Override
	public R process(GameSession gameSession, ServerRequest serverRequest) throws Exception {
		if (serverRequest.getData() == null || serverRequest.getData().equals("") || "{}".equals(serverRequest.getData())) {
			return R.fail("请输入正确的用户名密码");
		}
		Map<String, String> map = JSONObject.parseObject(serverRequest.getData(), Map.class);
		String account = map.get("account");
		String password = map.get("password");
		R userLogin = UserCache.getUserClient().userLogin(account, password);

		// 返回数据
		return super.typeJudge(gameSession, 200, userLogin, MsgCode.CODE_1.getResp(), "LoginMsgHandler - 用户登录");
	}
}

