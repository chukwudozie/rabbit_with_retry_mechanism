package com.emeka.rabbit_consumer.consumer;

import com.emeka.rabbit_consumer.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class DeadQueueConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RetryAccountingConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.guideline2.marketing.dead")
    public void listenForMarketing(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
            throws IOException {
        Employee employee = objectMapper.readValue(message.getBody(), Employee.class);
        LOG.info("On Marketing dead queue : " + employee);
        channel.basicAck(tag, false);
    }

    @RabbitListener(queues = "q.guideline2.accounting.dead")
    public void listenForAccounting(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
            throws IOException {
        Employee employee = objectMapper.readValue(message.getBody(), Employee.class);
        LOG.info("On Accounting dead queue : " + employee);
        channel.basicAck(tag, false);
    }

}
