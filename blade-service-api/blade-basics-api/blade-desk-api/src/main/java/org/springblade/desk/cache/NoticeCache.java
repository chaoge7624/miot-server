package org.springblade.desk.cache;

import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.desk.feign.INoticeClient;

/**
 * 客户端缓存
 * @author 李家民
 */
public class NoticeCache {

	private static INoticeClient noticeClient = null;

	public static INoticeClient getNoticeClient() {
		if (noticeClient == null) {
			noticeClient = SpringUtil.getBean(INoticeClient.class);
		}
		return noticeClient;
	}
}
