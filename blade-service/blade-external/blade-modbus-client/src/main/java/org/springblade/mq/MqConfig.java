package org.springblade.mq;

import org.springblade.common.config.MqQueueConfig;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置
 * @author 李家民
 */
@Configuration
public class MqConfig {

	@Bean("bootExchange")
	public Exchange bootExchange() {
		return ExchangeBuilder.directExchange(MqQueueConfig.ENERGY_EXCHANGE).durable(true).build();
	}

	@Bean("bootQueue")
	public Queue bootQueue() {
		return QueueBuilder.durable(MqQueueConfig.ENERGY_ELC_DATA_QUEUE).build();
	}

	@Bean
	public Binding bindQueueExchange(@Qualifier("bootQueue") Queue queue,
									 @Qualifier("bootExchange") Exchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(MqQueueConfig.ENERGY_ELC_DATA_QUEUE).noargs();
		// rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "routingKeyForDemo01", "message = " + tempNum);
	}

}
