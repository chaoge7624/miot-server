package org.springblade;

import org.springblade.core.cloud.client.BladeCloudApplication;
import org.springblade.core.launch.BladeApplication;

/**
 * 办公管理服务
 * @author 李家民
 */
@BladeCloudApplication
public class OfficeApplication {
	public static void main(String[] args) {
		// 告警
		// 工单
		// 楼层区域房间
		// 访客
		// 会议
		// 文档管理
		BladeApplication.run("blade-office", OfficeApplication.class, args);
	}
}

