package com.nhnacademy.springboot.familyProject.domain;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class HouseholdRequest {
    private Integer householdResidentSerialNumber;
    private LocalDate householdCompositionDate;
    private String householdCompositionReasonCode;
    private String currentHouseMovementAddress;
}
