package com.nhnacademy.springboot.familyProject.domain;

import com.nhnacademy.springboot.familyProject.entity.HouseholdCompositionResident;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class HouseholdRequest {
    private Integer householdResidentSerialNumber;
    private LocalDate householdCompositionDate;
    private String householdCompositionReasonCode;
    private String currentHouseMovementAddress;
    private List<HouseholdCompositionResidentDto> householdCompositionResidents;
}
