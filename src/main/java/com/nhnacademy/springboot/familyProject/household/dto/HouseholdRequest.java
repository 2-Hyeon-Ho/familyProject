package com.nhnacademy.springboot.familyProject.household.dto;

import com.nhnacademy.springboot.familyProject.householdCompositionResident.dto.HouseholdCompositionResidentResponse;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class HouseholdRequest {
    private Integer householdResidentSerialNumber;
    private LocalDate householdCompositionDate;
    private String householdCompositionReasonCode;
    private String currentHouseMovementAddress;
    private List<HouseholdCompositionResidentResponse> householdCompositionResidents;
}
