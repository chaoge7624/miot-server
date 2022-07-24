package org.springblade.system.cache;

import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.system.feign.IDictClient;

/**
 * 客户端缓存
 * @author 李家民
 */
public class DictCache {

	private static IDictClient dictClient = null;

	public static IDictClient getDictClient() {
		if (dictClient == null) {
			dictClient = SpringUtil.getBean(IDictClient.class);
		}
		return dictClient;
	}

}
