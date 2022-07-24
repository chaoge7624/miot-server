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
		BladeApplication.run("blade-office", OfficeApplication.class, args);
	}
}
