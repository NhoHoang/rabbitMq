package com.example.userservice.dto;

import com.example.userservice.converter.MemberTypeEnumConverter;
import com.example.userservice.entity.MemberType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Convert;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponseDTO {
    private long id;
    private String email;
    private Double salary;
    private MemberType memberType;
}
