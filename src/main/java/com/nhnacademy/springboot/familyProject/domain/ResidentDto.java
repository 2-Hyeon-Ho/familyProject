package com.nhnacademy.springboot.familyProject.domain;

import com.nhnacademy.springboot.familyProject.entity.Resident;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResidentDto {

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

    public static ResidentDto create(Resident resident) {
        return new ResidentDto(
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
