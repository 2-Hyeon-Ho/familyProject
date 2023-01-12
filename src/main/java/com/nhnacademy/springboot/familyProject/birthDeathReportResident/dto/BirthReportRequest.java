package com.nhnacademy.springboot.familyProject.birthDeathReportResident.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BirthReportRequest {
    private Integer residentSerialNumber;
    private LocalDate birthDeathReportDate;
    private String birthReportQualificationsCode;
    private String phoneNumber;
}
