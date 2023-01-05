package com.nhnacademy.springboot.familyProject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class HouseholdCompositionResidentDto {
    private Integer residentSerialNumber;
    private LocalDate reportDate;
    private String householdRelationshipCode;
    private String householdCompositionChangeReasonCode;
}
