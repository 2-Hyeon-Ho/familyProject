package com.nhnacademy.springboot.familyProject.domain;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BirthRegistrationRequest {
    private Integer residentSerialNumber;
    private LocalDate birthDeathReportDate;
    private String phoneNumber;
}
