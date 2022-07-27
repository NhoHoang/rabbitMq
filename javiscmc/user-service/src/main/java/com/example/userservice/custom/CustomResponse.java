package com.example.userservice.custom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    public static CustomResponse ok(Object data) {
        return CustomResponse.builder().code(HttpStatus.OK.value()).message(HttpStatusConstants.SUCCESS_MESSAGE).data(data).build();
    }

    public static CustomResponse error(int code, String message) {
        return CustomResponse.builder().code(code).message(message).build();
    }
    public static CustomResponse buildAll(int code, String message, Object data) {
        return CustomResponse.builder().code(code).message(message).data(data).build();
    }

}
