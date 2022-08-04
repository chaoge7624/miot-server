package org.springblade.netty.protocol.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 请求消息体
 * @author 李家民
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerRequest implements Serializable {
	private Integer code;
	private String data;
}
