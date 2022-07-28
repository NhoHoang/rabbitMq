package com.example.notification2service.service;


import com.example.notification2service.dto.UserInfo;

public interface EmailService {

    void sendSimpleMessage(UserInfo userInfo);
}
