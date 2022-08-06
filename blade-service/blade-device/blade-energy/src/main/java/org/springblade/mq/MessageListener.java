package org.springblade.mq;

import org.springblade.common.config.MqQueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;

/**
 * MQ消息监听
 * @author 李家民
 */
@Component
public class MessageListener {

	/**
	 * 能耗-电表-数据-队列
	 * 测试数据
	 * @param message
	 */
	@RabbitListener(queues = MqQueueConfig.ENERGY_ELC_DATA_QUEUE)
	public void demoQueue(Message message) {
		// 上面对队列名进行了绑定 消费者会监听这个队列的消息
		System.out.println("我是消费者 我收到的消息是：：" + new String(message.getBody()));
	}

}
