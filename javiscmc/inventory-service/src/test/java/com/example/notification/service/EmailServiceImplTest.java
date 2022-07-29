package com.example.notification.service;

import com.example.notification.dto.Mail;
import com.example.notification.dto.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

    @InjectMocks
    EmailServiceImpl emailService;

    @Mock
    JavaMailSender emailSender;

    UserInfo userInfo = new UserInfo();

    @BeforeEach
    public void setup() {
        userInfo.setEmail("nho1@yopmail.com");
        userInfo.setSalary(105000.0);
        userInfo.setMemberType("PLATINUM");
    }

    @Test
    void sendEmailOnce() {

        emailService.sendSimpleMessage(userInfo);
        verify(emailSender, Mockito.atLeastOnce()).send(any(SimpleMailMessage.class));

    }

}