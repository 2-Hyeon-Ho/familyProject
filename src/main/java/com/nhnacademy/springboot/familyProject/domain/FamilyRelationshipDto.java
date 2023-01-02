package com.nhnacademy.springboot.familyProject.domain;

import com.nhnacademy.springboot.familyProject.entity.FamilyRelationship;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FamilyRelationshipDto {
    private Integer baseResidentSerialNumber;
    private Integer familyResidentRegistrationNumber;
    private String familyRelationshipCode;

    public static FamilyRelationshipDto create(FamilyRelationship familyRelationship) {
        return new FamilyRelationshipDto(
                familyRelationship.getPk().getBaseResidentSerialNumber(),
                familyRelationship.getPk().getFamilyResidentRegistrationNumber(),
                familyRelationship.getFamilyRelationshipCode()
        );
    }
}
