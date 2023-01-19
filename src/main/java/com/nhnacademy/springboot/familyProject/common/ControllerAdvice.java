package com.nhnacademy.springboot.familyProject.common;

import com.nhnacademy.springboot.familyProject.exception.AcceptHeaderNotValidException;
import com.nhnacademy.springboot.familyProject.exception.CustomException;
import com.nhnacademy.springboot.familyProject.exception.DataDuplicateException;
import com.nhnacademy.springboot.familyProject.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorResponse> handleRequestException(CustomException e) {
        log.error("[handleCustomException] {} : {}", e.getErrorCode().name(), e.getErrorCode().getErrorMessage());
        return ResponseEntity.status(ErrorResponse.error(e).getStatusCode())
            .header("Content-Type", "application/json")
            .body(ErrorResponse.error(e).getBody());
    }
    @ExceptionHandler(value = AcceptHeaderNotValidException.class)
    public ResponseEntity<ErrorResponse> handleAcceptHeaderException(Exception e) {
        log.error("[handleAcceptHeaderException] {} : {}", HttpStatus.BAD_REQUEST.name(), e.getMessage());
        final ErrorResponse response = new ErrorResponse(
            HttpStatus.BAD_REQUEST,
            HttpStatus.BAD_REQUEST.name(),
            e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .header("Content-Type", "application/json")
            .body(response);
    }
    @ExceptionHandler(value = DataDuplicateException.class)
    public ResponseEntity<ErrorResponse> handleDataDuplicateException(Exception e) {
        log.error("[handleDataDuplicateException] {} : {}", HttpStatus.CONFLICT.name(), e.getMessage());
        final ErrorResponse response = new ErrorResponse(
            HttpStatus.CONFLICT,
            HttpStatus.CONFLICT.name(),
            e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .header("Content-Type", "application/json")
            .body(response);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity handleException() {
        return ResponseEntity.internalServerError().build();
    }
}
