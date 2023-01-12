package com.nhnacademy.springboot.familyProject.familyRelationship.dto;

import com.nhnacademy.springboot.familyProject.familyRelationship.domain.FamilyRelationship;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FamilyRelationshipResponse {
    private Integer baseResidentSerialNumber;
    private Integer familyResidentRegistrationNumber;
    private String familyRelationshipCode;

    public static FamilyRelationshipResponse create(FamilyRelationship familyRelationship) {
        return new FamilyRelationshipResponse(
                familyRelationship.getPk().getBaseResidentSerialNumber(),
                familyRelationship.getPk().getFamilyResidentRegistrationNumber(),
                familyRelationship.getFamilyRelationshipCode()
        );
    }
}
