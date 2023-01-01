package com.nhnacademy.springboot.familyProject.exception;

public class AcceptHeaderNotValidException extends RuntimeException {
    public AcceptHeaderNotValidException(String accept) {
        super(accept + "is not valid");
    }
}
