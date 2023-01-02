package com.nhnacademy.springboot.familyProject.domain;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class FamilyRelationshipUpdateRequest {
    @NotBlank
    @Size(max = 20)
    private String relationShip;
}
