package com.nhnacademy.springboot.familyProject.exception;

public class HouseholdMovementAddressNotFoundException extends RuntimeException {
    public HouseholdMovementAddressNotFoundException() {
        super("householdMovementAddress is not found!");
    }
}
