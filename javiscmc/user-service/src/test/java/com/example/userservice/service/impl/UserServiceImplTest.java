package com.example.userservice.service.impl;

import com.example.userservice.dto.UserRequestDTO;
import com.example.userservice.dto.UserResponseDTO;
import com.example.userservice.entity.MemberType;
import com.example.userservice.entity.User;
import com.example.userservice.exception.BusinessException;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.mapper.UserResponseMapper;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.utils.ConvertJsonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;
    @Mock
    ConvertJsonUtils convertJsonUtils;
    @Mock
    UserMapper mapper;
//    @Mock
//    KafkaProducer producer;
    @Mock
    UserResponseMapper responseMapper;
    @Mock
    PasswordEncoder passwordEncoder;

    UserRequestDTO userRequestDTO = new UserRequestDTO();

    @BeforeEach
    public void setup() {

        userRequestDTO.setEmail("nho1@yopmail.com");
        userRequestDTO.setPassword("5555ffff");
        userRequestDTO.setSalary(105000.0);
    }

    @Test
    void whenCreateUserExisted_shouldThrowException() {
        when(userRepository.existsByEmail(userRequestDTO.getEmail()))
                .thenReturn(true);

        Assertions.assertThrows(
                BusinessException.class,
                () -> userService.createUser(userRequestDTO));
    }

//    @Test
//    void whenUserNotExist_shouldSaveUser() {
//        when(userRepository.existsByEmail(userRequestDTO.getEmail()))
//                .thenReturn(false);
//
//        User requestUser = new User();
//        requestUser.setEmail("nho1@yopmail.com");
//        requestUser.setSalary(105000.0);
//        requestUser.setMemberType(MemberType.PLATINUM);
//
//        User savedUser = new User();
//        savedUser.setId(1L);
//        savedUser.setEmail("nho1@yopmail.com");
//        savedUser.setSalary(105000.0);
//        savedUser.setMemberType(MemberType.PLATINUM);
//
//        UserResponseDTO responseDto = new UserResponseDTO();
//        responseDto.setId(1L);
//        responseDto.setEmail("nho1@yopmail.com");
//        responseDto.setSalary(105000.0);
//        responseDto.setMemberType(MemberType.PLATINUM);
//
//        when(mapper.toEntity(userRequestDTO)).thenReturn(requestUser);
//        when(userRepository.save(requestUser)).thenReturn(savedUser);
//        when(responseMapper.toDto(savedUser)).thenReturn(responseDto);
//
//        UserResponseDTO UserResponseDTO = userService.createUser(userRequestDTO);
//        verify(userRepository, Mockito.times(1)).save(any(User.class));
//        verify(producer, Mockito.times(1)).send(any());
//        Assertions.assertEquals(1L,UserResponseDTO.getId());
//        Assertions.assertEquals("nho1@yopmail.com",UserResponseDTO.getEmail());
//    }

    @Test
    void getMemberTypeSilverFromSalary(){
        Double salary = 30000.0;
        MemberType memberType = userService.getMemberTypeFromSalary(salary);
        Assertions.assertEquals(MemberType.SILVER,memberType);
    }

    @Test
    void getMemberTypeGoldFromSalary(){
        Double salary = 50000.0;
        MemberType memberType = userService.getMemberTypeFromSalary(salary);
        Assertions.assertEquals(MemberType.GOLD,memberType);
    }

    @Test
    void getMemberTypePlatinumFromSalary(){
        Double salary = 100000.0;
        MemberType memberType = userService.getMemberTypeFromSalary(salary);
        Assertions.assertEquals(MemberType.PLATINUM,memberType);
    }

    @Test
    void getAllUsers_shouldReturnPageOfUsers() {
        Pageable pageable = PageRequest.of(0, 5);
        List<User> userData = List.of(new User(),new User(),new User(),new User(),new User());
        Page<User> userPage = new PageImpl(userData, pageable, userData.size());

        when(userRepository.findAll(pageable)).thenReturn(userPage);
        when(responseMapper.toDto(any())).thenReturn(new UserResponseDTO());

        Page<UserResponseDTO> results = userService.getAllUsers(pageable);

        Assertions.assertEquals(5, results.getTotalElements());
    }

}