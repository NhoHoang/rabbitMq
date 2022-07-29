package com.example.notification.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mail {

    private String toEmail;
    private String subject;
    private String text;

}

