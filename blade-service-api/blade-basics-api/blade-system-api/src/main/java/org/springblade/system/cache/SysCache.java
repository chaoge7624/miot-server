package org.springblade.system.cache;

import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.system.feign.ISysClient;

public class SysCache {

	private static ISysClient sysClient;

	public static ISysClient getSysClient() {
		if (sysClient == null) {
			sysClient = SpringUtil.getBean(ISysClient.class);
		}
		return sysClient;
	}
}
