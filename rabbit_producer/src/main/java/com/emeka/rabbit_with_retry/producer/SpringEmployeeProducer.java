package com.emeka.rabbit_with_retry.producer;

import com.emeka.rabbit_with_retry.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SpringEmployeeProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void sendMessage(Employee data) throws IOException {
		var json = objectMapper.writeValueAsString(data);
		
		rabbitTemplate.convertAndSend("x.spring2.work", "", json);
	}
	
}