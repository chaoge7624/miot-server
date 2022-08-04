package org.springblade.netty.protocol.response;

import org.springblade.common.tool.json.JsonUtilTool;

import java.io.IOException;

/**
 * 消息返回模板
 * @author 李家民
 */
public class SendResponse extends ServerResponse {

	/**
	 * 消息返回模板
	 * @param status  消息状态 例如：200 成功
	 * @param data    实体数据
	 * @param msgCode 返回码
	 * @param detail  对这条数据的描述信息
	 */
	public SendResponse(int status, Object data, int msgCode, String detail) {
		super(status, msgCode);
		try {
			if (status > 0) {
				output.writeUTF(JsonUtilTool.toJson(data));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			output.close();
		}
	}
}
