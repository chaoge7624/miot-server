package org.springblade.system.user.cache;

import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.system.user.feign.IUserClient;

public class UserCache {

	private static IUserClient userClient;

	public static IUserClient getUserClient() {
		if (userClient == null) {
			userClient = SpringUtil.getBean(IUserClient.class);
		}
		return userClient;
	}
}
