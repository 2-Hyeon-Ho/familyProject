package com.nhnacademy.springboot.familyProject.familyRelationship.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class FamilyRelationshipCreateRequest {
    @NotNull
    private Integer familySerialNumber;
    @NotBlank
    @Size(max = 20)
    private String relationShip;
}
