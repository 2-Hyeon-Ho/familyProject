package com.nhnacademy.springboot.familyProject.householdMovementAddress.dto;

import com.nhnacademy.springboot.familyProject.householdMovementAddress.domain.HouseholdMovementAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class HouseholdMovementAddressDto {
    private String householdResidentName;
    private LocalDate houseMovementReportDate;
    private String houseMovementAddress;
    private String lastAddressYn;

    public static HouseholdMovementAddressDto create(HouseholdMovementAddress householdMovementAddress) {
        return new HouseholdMovementAddressDto(
                householdMovementAddress.getHouseHold().getResident().getName(),
                householdMovementAddress.getPk().getHouseMovementReportDate(),
                householdMovementAddress.getHouseMovementAddress(),
                householdMovementAddress.getLastAddressYn()
        );
    }
}
