package com.nhnacademy.springboot.familyProject.domain;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class HouseholdMovementAddressRequest {
    private LocalDate houseMovementReportDate;
    private String houseMovementAddress;
}
