package com.example.userservice.service.impl;


import com.example.userservice.custom.HttpStatusConstants;
import com.example.userservice.dto.UserResponseDTO;
import com.example.userservice.dto.UserRequestDTO;
import com.example.userservice.entity.MemberType;
import com.example.userservice.entity.User;
import com.example.userservice.exception.BusinessException;
import com.example.userservice.kafka.producer.KafkaProducer;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.mapper.UserResponseMapper;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import com.example.userservice.utils.ConvertJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    private UserMapper mapper;

    private UserResponseMapper responseMapper;

    private static final double SILVER_STANDARD = 30000;

    private static final double GOLDEN_STANDARD = 50000;

    private final KafkaProducer producer;

    private ConvertJsonUtils convertJsonUtils;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, UserMapper mapper,
                           KafkaProducer producer, ConvertJsonUtils convertJsonUtils,
                           UserResponseMapper responseMapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.producer = producer;
        this.convertJsonUtils = convertJsonUtils;
        this.responseMapper = responseMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        // CHECK EMAIL EXISTS OR NOT
        if (repository.existsByEmail(dto.getEmail())) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.value(),
                    HttpStatusConstants.EMAIL_EXIST_MESSAGE);
        }
        User user = mapper.toEntity(dto);

        MemberType memberType = getMemberTypeFromSalary(dto.getSalary());
        user.setMemberType(memberType);
        user.setPassword(this.passwordEncoder.encode(dto.getPassword()));

        user = repository.save(user);

        UserResponseDTO userResponseDTO = responseMapper.toDto(user);

        //kafka sending
        String message = convertJsonUtils.convertObjToString(userResponseDTO);
        producer.send(message);
        return userResponseDTO;
    }

    public MemberType getMemberTypeFromSalary(Double salary) {
        MemberType memberType;
        if (salary <= SILVER_STANDARD) {
            memberType = MemberType.SILVER;
        } else if (salary <= GOLDEN_STANDARD) {
            memberType = MemberType.GOLD;
        } else {
            memberType = MemberType.PLATINUM;
        }
        return memberType;
    }

    @Override
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {

        return repository.findAll(pageable).map(responseMapper::toDto);

    }
}
