package com.nhnacademy.springboot.familyProject.exception;

public class HouseholdNotFoundException extends RuntimeException {
    public HouseholdNotFoundException() {
        super("household is not found!");
    }
}
