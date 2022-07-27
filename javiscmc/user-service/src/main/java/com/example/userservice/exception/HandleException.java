package com.example.userservice.exception;
import com.example.userservice.custom.CustomResponse;
import com.example.userservice.custom.HttpStatusConstants;
import com.example.userservice.utils.ConvertJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class HandleException {
    public HandleException(ConvertJsonUtils convertJsonUtils) {
        this.convertJsonUtils = convertJsonUtils;
    }

    private ConvertJsonUtils convertJsonUtils;

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CustomResponse handleBusinessException(BusinessException businessException) {
        CustomResponse response = CustomResponse.error(businessException.getCode(), businessException.getMessage());
        return response;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CustomResponse invalidRequestExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return CustomResponse.buildAll(HttpStatus.BAD_REQUEST.value(), HttpStatusConstants.BAD_REQUEST_MESSAGE,errors);
    }

    @ExceptionHandler({IllegalStateException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CustomResponse handleIllegalException(IllegalStateException illegalStateException) {
        log.error("IllegalStateException => rootCause: {}", Arrays.stream(illegalStateException.getStackTrace()).findFirst());
        log.error("IllegalStateException => localizedMessage: {}", illegalStateException.getMessage());
        CustomResponse response = CustomResponse.error(HttpStatus.BAD_REQUEST.value(), HttpStatusConstants.BAD_REQUEST_MESSAGE);
        return response;
    }

    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CustomResponse handleNullPointerException(NullPointerException nullPointerException) {
        log.error("NullPointerException => rootCause: {}", Arrays.stream(nullPointerException.getStackTrace()).findFirst());
        log.error("NullPointerException => localizedMessage: {}", nullPointerException.getMessage());
        CustomResponse response = CustomResponse.error(HttpStatus.BAD_REQUEST.value(), HttpStatusConstants.BAD_REQUEST_MESSAGE);
        return response;
    }

    @ExceptionHandler({SQLException.class, Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomResponse handleSQLException(SQLException sqlException, Exception exception) {
        CustomResponse response = new CustomResponse();

        if (exception != null) {
            response = CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatusConstants.UNAVAILABLE_MESSAGE);
            log.error(convertJsonUtils.convertObjToString(response));
            log.error("Exception => rootCause: {}", Arrays.stream(exception.getStackTrace()).findFirst());
            log.error("Exception => Cause: {}", exception.getMessage());
            log.error("Exception =>", exception.getStackTrace());
        }
        if (sqlException != null) {
            response = CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatusConstants.UNAVAILABLE_MESSAGE);
            log.error("Exception => rootCause: {}", Arrays.stream(sqlException.getStackTrace()).findFirst());
            log.error("SQLException => Cause:", sqlException.getStackTrace());
        }
        return response;
    }

}






