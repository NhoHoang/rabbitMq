package com.example.userservice.converter;


import com.example.userservice.entity.MemberType;

import javax.persistence.AttributeConverter;

public class MemberTypeEnumConverter implements AttributeConverter<MemberType,String> {
    @Override
    public String convertToDatabaseColumn(MemberType attribute) {
        return attribute.getValue();
    }

    @Override
    public MemberType convertToEntityAttribute(String dbData) {
        return MemberType.of(dbData);
    }
}