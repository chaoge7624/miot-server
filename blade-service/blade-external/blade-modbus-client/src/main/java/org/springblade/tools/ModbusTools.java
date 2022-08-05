package org.springblade.tools;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;

import java.util.HashMap;
import java.util.Map;

/**
 * modbus处理器
 * @author 李家民
 */
public class ModbusTools {

	/** 工厂构建 */
	private static ModbusFactory modbusFactory = new ModbusFactory();
	private static Map<String, ModbusMaster> modbusMasterMap = new HashMap<>(18);

	/**
	 * 获取master
	 * @param host 地址
	 * @param port 端口
	 * @return
	 * @throws ModbusInitException
	 */
	private static ModbusMaster getMaster(String host, int port) {
		try {
			ModbusMaster modbusMaster = modbusMasterMap.get(host);
			if (modbusMaster == null) {
				// 构建ModbusMaster --- TCP
				modbusMaster = buildTcpMaster(host, port);
				modbusMasterMap.put(host, modbusMaster);

				// 场景二：RTU 协议
				// modbusFactory.createRtuMaster(wapper);

				// 场景三：UDP 协议
				// modbusFactory.createUdpMaster(params);

				// 场景四：ASCII 协议
				// modbusFactory.createAsciiMaster(wrapper);

			} else {
				if (modbusMaster.isConnected()) {
					return modbusMaster;
				} else {
					// 链接失活 重新构建ModbusMaster
					modbusMaster = buildTcpMaster(host, port);
					modbusMasterMap.put(host, modbusMaster);
				}
			}
			return modbusMaster;
		} catch (ModbusInitException modbusInitException) {
			// 构建出现异常
			modbusInitException.printStackTrace();
			return null;
		}
	}

	/**
	 * 构建TCP Master
	 * @param host 地址
	 * @param port 端口
	 * @return ModbusMaster
	 */
	private static ModbusMaster buildTcpMaster(String host, int port) throws ModbusInitException {
		IpParameters params = new IpParameters();
		// 地址及端口
		params.setHost(host);
		params.setPort(port);
		// 长短链接根据实际需求选择
		ModbusMaster master = modbusFactory.createTcpMaster(params, true);
		// 超时设置
		master.setTimeout(5000);
		master.init();
		return master;
	}

	/**
	 * 读线圈
	 * 读取[01 Coil Status 0x]类型 开关数据
	 * @param host    地址
	 * @param port    端口
	 * @param slaveId 模拟仿真器只有一个id都是1
	 * @param offset  偏移位置
	 * @return 读取值
	 * @throws ModbusTransportException 异常
	 * @throws ErrorResponseException   异常
	 * @throws ModbusInitException      异常
	 */
	public static Boolean readCoilStatus(String host, int port, int slaveId, int offset)
		throws ModbusTransportException, ErrorResponseException, ModbusInitException {
		// 01 Coil Status
		BaseLocator<Boolean> loc = BaseLocator.coilStatus(slaveId, offset);
		Boolean value = getMaster(host, port).getValue(loc);
		return value;
	}

	/**
	 * 读离散状态
	 * 读取[02 Input Status 1x]类型 开关数据
	 * @param host    地址
	 * @param port    端口
	 * @param slaveId 模拟仿真器只有一个id都是1
	 * @param offset
	 * @return
	 * @throws ModbusTransportException
	 * @throws ErrorResponseException
	 * @throws ModbusInitException
	 */
	public static Boolean readInputStatus(String host, int port, int slaveId, int offset)
		throws ModbusTransportException, ErrorResponseException, ModbusInitException {
		// 02 Input Status
		BaseLocator<Boolean> loc = BaseLocator.inputStatus(slaveId, offset);
		Boolean value = getMaster(host, port).getValue(loc);
		return value;
	}

	/**
	 * 保持寄存器
	 * 读取[03 Holding Register类型 2x]模拟量数据
	 * @param host     地址
	 * @param port     端口
	 * @param slaveId  模拟仿真器只有一个id都是1
	 * @param offset   位置
	 * @param dataType 数据类型,来自com.serotonin.modbus4j.code.DataType
	 * @return
	 * @throws ModbusTransportException 异常
	 * @throws ErrorResponseException   异常
	 * @throws ModbusInitException      异常
	 */
	public static Number readHoldingRegister(String host, int port, int slaveId, int offset, int dataType)
		throws ModbusTransportException, ErrorResponseException, ModbusInitException {
		// 03 Holding Register类型数据读取
		BaseLocator<Number> loc = BaseLocator.holdingRegister(slaveId, offset, dataType);
		Number value = getMaster(host, port).getValue(loc);
		return value;
	}

	/**
	 * 读取[04 Input Registers 3x]类型 模拟量数据
	 * @param host     地址
	 * @param port     端口
	 * @param slaveId  slaveId
	 * @param offset   位置
	 * @param dataType 数据类型,来自com.serotonin.modbus4j.code.DataType
	 * @return 返回结果
	 * @throws ModbusTransportException 异常
	 * @throws ErrorResponseException   异常
	 * @throws ModbusInitException      异常
	 */
	public static Number readInputRegisters(String host, int port, int slaveId, int offset, int dataType)
		throws ModbusTransportException, ErrorResponseException, ModbusInitException {
		// 04 Input Registers类型数据读取
		BaseLocator<Number> loc = BaseLocator.inputRegister(slaveId, offset, dataType);
		Number value = getMaster(host, port).getValue(loc);
		return value;
	}

	/**
	 * 批量读取使用方法
	 * @param host 地址
	 * @param port 端口
	 * @throws ModbusTransportException
	 * @throws ErrorResponseException
	 * @throws ModbusInitException
	 */
	public static void batchRead(String host, int port) throws ModbusTransportException, ErrorResponseException, ModbusInitException {
		BatchRead<Integer> batch = new BatchRead<Integer>();
		batch.addLocator(0, BaseLocator.holdingRegister(1, 1, DataType.FOUR_BYTE_FLOAT));
		batch.addLocator(1, BaseLocator.inputStatus(1, 0));
		ModbusMaster master = getMaster(host, port);
		batch.setContiguousRequests(false);
		BatchResults<Integer> results = master.send(batch);
		System.out.println(results.getValue(0));
		System.out.println(results.getValue(1));
	}

}
