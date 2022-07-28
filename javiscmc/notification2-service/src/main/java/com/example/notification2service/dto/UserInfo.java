package com.example.notification2service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    private long id;
    private String email;
    private Double salary;
    private String memberType;
}
