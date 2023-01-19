package com.nhnacademy.springboot.familyProject.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhnacademy.springboot.familyProject.common.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {

    private final HttpStatus status;
    private final String code;
    private final String message;


    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.name();
        this.message = errorCode.getErrorMessage();
    }

    public static ResponseEntity<ErrorResponse> error(CustomException e) {
        return ResponseEntity
            .status(e.getErrorCode().getStatus())
            .body(ErrorResponse.builder()
                .status(e.getErrorCode().getStatus())
                .code(e.getErrorCode().name())
                .message(e.getErrorCode().getErrorMessage())
                .build());
    }
}
