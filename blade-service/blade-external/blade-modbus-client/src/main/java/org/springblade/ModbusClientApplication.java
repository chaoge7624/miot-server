package org.springblade;

import org.springblade.core.cloud.client.BladeCloudApplication;
import org.springblade.core.launch.BladeApplication;

/**
 * Modbus客户端引入
 * @author 李家民
 */
@BladeCloudApplication
public class ModbusClientApplication {
	public static void main(String[] args) {
		BladeApplication.run("blade-modbus-client", ModbusClientApplication.class, args);
	}
}
