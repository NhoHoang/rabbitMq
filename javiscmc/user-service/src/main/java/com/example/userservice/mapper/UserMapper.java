package com.example.userservice.mapper;

import com.example.userservice.dto.UserRequestDTO;
import com.example.userservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserRequestDTO, User>{
}
