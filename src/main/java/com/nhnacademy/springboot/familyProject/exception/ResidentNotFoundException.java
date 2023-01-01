package com.nhnacademy.springboot.familyProject.exception;

public class ResidentNotFoundException extends RuntimeException{
    public ResidentNotFoundException() {
        super("resident is not found!");
    }
}
