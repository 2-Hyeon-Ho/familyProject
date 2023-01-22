package com.nhnacademy.springboot.familyProject.resident.dto;

import com.nhnacademy.springboot.familyProject.resident.domain.Resident;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResidentResponse {

    private Integer residentId;

    private String name;

    private String residentRegistrationNumber;

    private String genderCode;

    private LocalDateTime birthDate;

    private String birthPlaceCode;

    private String registrationBaseAddress;

    private LocalDateTime deathDate;

    private String deathPlaceCode;

    private String deathPlaceAddress;

    private String id;

    private String email;

    public ResidentResponse(Resident resident) {
        this.residentId = resident.getResidentId();
        this.name = resident.getName().getName();
        this.residentRegistrationNumber = resident.getResidentRegistrationNumber()
            .getResidentRegistrationNumber();
        this.genderCode = resident.getGenderCode().name();
        this.birthDate = resident.getBirthDate();
        this.birthPlaceCode = resident.getBirthPlaceCode().name();
        this.registrationBaseAddress = resident.getRegistrationBaseAddress();
        this.deathDate = resident.getDeathDate();
        this.deathPlaceCode = resident.getDeathPlaceCode().name();
        this.deathPlaceAddress = resident.getDeathPlaceAddress();
        this.id = resident.getId().getId();
        this.email = resident.getEmail().getEmail();
    }
}
