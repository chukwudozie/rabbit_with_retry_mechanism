package com.emeka.rabbit_with_retry.producer;

import com.emeka.rabbit_with_retry.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringPictureProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage (Picture picture) throws JsonProcessingException {
        // convert Picture to JSON  String using object Mapper
        String pictureString  = objectMapper.writeValueAsString(picture);
        // params - exchange name, routing key, String of message
        rabbitTemplate.convertAndSend("x.guideline.work",picture.getType(),pictureString);
    }
}
