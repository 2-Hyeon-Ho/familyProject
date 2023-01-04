package com.nhnacademy.springboot.familyProject.domain;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DeathReportRequest {
    private Integer residentSerialNumber;
    private LocalDate birthDeathReportDate;
    private String deathReportQualificationsCode;
    private String phoneNumber;
}
