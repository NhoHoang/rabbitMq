package com.example.userservice.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,6}$",message = "Email invalid")
    private String email;

    @NotBlank(message = "Password is required")
    @Length(min = 8, max = 20, message = "Password length should be between 8, 20 chars")
    private String password;

    @NotNull(message = "Salary is required")
    @Min(value = 15000,message = "Basic salary is below require")
    private Double salary;
}
