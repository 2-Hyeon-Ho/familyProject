package com.nhnacademy.springboot.familyProject.resident.dto;

import com.nhnacademy.springboot.familyProject.resident.domain.Resident;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResidentResponse {

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

    public static ResidentResponse create(Resident resident) {
        return new ResidentResponse(
                resident.getName(),
                resident.getResidentRegistrationNumber(),
                resident.getGenderCode(),
                resident.getBirthDate(),
                resident.getBirthPlaceCode(),
                resident.getRegistrationBaseAddress(),
                resident.getDeathDate(),
                resident.getDeathPlaceCode(),
                resident.getDeathPlaceAddress(),
                resident.getId(),
                resident.getEmail()
        );
    }
}
