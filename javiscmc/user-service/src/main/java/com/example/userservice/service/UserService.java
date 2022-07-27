package com.example.userservice.service;

import com.example.userservice.dto.UserResponseDTO;
import com.example.userservice.dto.UserRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO dto);

    Page<UserResponseDTO> getAllUsers(Pageable pageable);
}
