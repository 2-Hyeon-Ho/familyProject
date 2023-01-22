package com.nhnacademy.springboot.familyProject.exception;

public class DataDuplicateException extends RuntimeException {
    public DataDuplicateException(String data) {
        super(data + "이/가 이미 존재합니다!");
    }
}
