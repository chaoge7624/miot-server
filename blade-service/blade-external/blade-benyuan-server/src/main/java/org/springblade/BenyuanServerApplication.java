package org.springblade;

import org.springblade.core.cloud.client.BladeCloudApplication;
import org.springblade.core.launch.BladeApplication;

/**
 * 本原用Server端接口
 * @author 李家民
 */
@BladeCloudApplication
public class BenyuanServerApplication {
	public static void main(String[] args) {
		BladeApplication.run("blade-benyuan-server", BenyuanServerApplication.class, args);
	}
}
