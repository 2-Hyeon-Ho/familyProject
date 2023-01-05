package com.nhnacademy.springboot.familyProject.domain;

import com.nhnacademy.springboot.familyProject.entity.Household;
import com.nhnacademy.springboot.familyProject.entity.HouseholdCompositionResident;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class HouseholdDto {
    private String householdResidentName;
    private String currentHouseMovementAddress;
    private List<HouseholdCompositionResidentDto> householdCompositionResidents;

    public static HouseholdDto create(Household household, List<HouseholdCompositionResidentDto> householdCompositionResident) {
        return new HouseholdDto(
                household.getResident().getName(),
                household.getCurrentHouseMovementAddress(),
                householdCompositionResident
        );
    }
}
