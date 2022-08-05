package org.springblade;

import org.springblade.core.cloud.client.BladeCloudApplication;
import org.springblade.core.launch.BladeApplication;

@BladeCloudApplication
public class EnvironmentApplication {
	public static void main(String[] args) {
		BladeApplication.run("blade-environment", EnvironmentApplication.class, args);
	}
}
