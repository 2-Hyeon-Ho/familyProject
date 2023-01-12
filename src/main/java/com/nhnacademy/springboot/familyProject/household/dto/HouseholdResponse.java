package com.nhnacademy.springboot.familyProject.household.dto;

import com.nhnacademy.springboot.familyProject.householdCompositionResident.dto.HouseholdCompositionResidentResponse;
import com.nhnacademy.springboot.familyProject.household.domain.Household;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HouseholdResponse {
    private String householdResidentName;
    private String currentHouseMovementAddress;
    private List<HouseholdCompositionResidentResponse> householdCompositionResidents;

    public static HouseholdResponse create(Household household, List<HouseholdCompositionResidentResponse> householdCompositionResident) {
        return new HouseholdResponse(
                household.getResident().getName(),
                household.getCurrentHouseMovementAddress(),
                householdCompositionResident
        );
    }
}
