package com.nhnacademy.springboot.familyProject.exception;

public class BirthDeathReportResidentNotFoundException extends RuntimeException {
    public BirthDeathReportResidentNotFoundException() {
        super("birthDeathReportResident is not found!");
    }
}
