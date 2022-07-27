package com.example.notification.kafka;

import com.example.notification.dto.UserInfo;
import com.example.notification.service.EmailService;
import com.example.notification.utils.ConvertJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    private EmailService emailService;
    private ConvertJsonUtils convertJsonUtils;

    public KafkaConsumer(ConvertJsonUtils convertJsonUtils, EmailService emailService ) {
        this.convertJsonUtils = convertJsonUtils;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "${kafka.topic.id}", groupId = "${kafka.group.id}")
    public void consumer(final ConsumerRecord<String, UserInfo> consumerRecord) {
        log.info("key: " + consumerRecord.key());
        log.info("Headers: " + consumerRecord.headers());
        log.info("Partition: " + consumerRecord.partition());
        log.info("Created User: " + consumerRecord.value());
        String string = String.valueOf(consumerRecord.value());
        UserInfo userInfo = convertJsonUtils.convertStringToObject(string,UserInfo.class);
        System.out.println(userInfo);
        emailService.sendSimpleMessage(userInfo);

    }

}