package com.example.notification.dto;

import com.example.notification.config.MQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageListener {

    RabbitTemplate template;

    public MessageListener(RabbitTemplate template) {
        this.template = template;
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(CustomMessage message) {
        System.out.println(message);
        CustomMessage sendingMessage = new CustomMessage();
        sendingMessage.setMessageId(UUID.randomUUID().toString());
        sendingMessage.setMessage("Service 1 đã nhận được");
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, sendingMessage);
    }

}