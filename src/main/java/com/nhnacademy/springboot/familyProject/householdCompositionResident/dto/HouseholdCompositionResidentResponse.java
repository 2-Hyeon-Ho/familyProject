package com.nhnacademy.springboot.familyProject.householdCompositionResident.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class HouseholdCompositionResidentResponse {
    private Integer residentSerialNumber;
    private LocalDate reportDate;
    private String householdRelationshipCode;
    private String householdCompositionChangeReasonCode;
}
