package org.springblade;

import org.springblade.core.cloud.client.BladeCloudApplication;
import org.springblade.core.launch.BladeApplication;

/**
 * 能耗模块
 * @author 李家民
 */
@BladeCloudApplication
public class EnergyApplication {
	public static void main(String[] args) {
		BladeApplication.run("blade-energy", EnergyApplication.class, args);
	}
}
