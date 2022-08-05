package org.springblade;

import org.springblade.core.cloud.client.BladeCloudApplication;
import org.springblade.core.launch.BladeApplication;

/**
 * 灯模块
 * @author 李家民
 */
@BladeCloudApplication
public class LampApplication {
	public static void main(String[] args) {
		BladeApplication.run("blade-lamp", LampApplication.class, args);
	}
}
