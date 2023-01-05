package com.nhnacademy.springboot.familyProject.domain;

import com.nhnacademy.springboot.familyProject.entity.Household;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class HouseholdDto {
    private String householdResidentName;
    private LocalDate householdCompositionDate;
    private String householdCompositionReasonCode;
    private String currentHouseMovementAddress;

    public static HouseholdDto create(Household household) {
        return new HouseholdDto(
                household.getResident().getName(),
                household.getHouseholdCompositionDate(),
                household.getHouseholdCompositionReasonCode(),
                household.getCurrentHouseMovementAddress()
        );
    }
}
