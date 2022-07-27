package com.example.userservice.controller;

import com.example.userservice.custom.CustomResponse;
import com.example.userservice.custom.HttpStatusConstants;
import com.example.userservice.dto.UserResponseDTO;
import com.example.userservice.dto.UserRequestDTO;
import com.example.userservice.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getAllUser3(Pageable pageable) {
        Page<UserResponseDTO> userPage = service.getAllUsers(pageable);
        return ResponseEntity.ok(userPage);
    }

    @PostMapping("")
    public CustomResponse<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO dto) {
        var createUserInfo = service.createUser(dto);
        return new CustomResponse(HttpStatus.OK.value(), HttpStatusConstants.SUCCESS_MESSAGE, createUserInfo);
    }

}
