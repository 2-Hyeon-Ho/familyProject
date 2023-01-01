package com.nhnacademy.springboot.familyProject.exception;

public class DataDuplicateException extends RuntimeException {
    public DataDuplicateException(String data) {
        super(data + " is exist!");
    }
}
