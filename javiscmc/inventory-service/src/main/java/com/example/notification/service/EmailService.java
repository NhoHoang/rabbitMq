package com.example.notification.service;


import com.example.notification.dto.UserInfo;

public interface EmailService {

    void sendSimpleMessage(UserInfo userInfo);
}
