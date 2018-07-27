package com.example.rabbitmqdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMessageSender {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderMessageSender.class);
	/*
	 * Spring Boot auto-configures the infrastructure beans required to send/receive 
	 * messages to/from RabbitMQ broker. We can simply autowire RabbitTemplate and send a 
	 * message by invoking rabbitTemplate.convertAndSend(“routingKey”, Object) method.
	 */
	
	/*
	 * Here default serialization mechanism converts the message object 
	 * into byte[] using SimpleMessageConverter and on the receiving end, it will deserialize 
	 * byte[] into the Object type (in our case Order) using GenericMessageConverter.
	 */
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendOrder(Order order) {
		LOGGER.info("Sending message : " + order);
		this.rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_SIMPLE_ORDERS, order);
	}
	
}
