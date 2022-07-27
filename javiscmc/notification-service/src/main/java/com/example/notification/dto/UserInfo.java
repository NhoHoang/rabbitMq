package com.example.notification.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    private long id;
    private String email;
    private Double salary;
    private String memberType;
}
