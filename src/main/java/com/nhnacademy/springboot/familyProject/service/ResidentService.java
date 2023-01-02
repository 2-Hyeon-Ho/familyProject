package com.nhnacademy.springboot.familyProject.service;

import com.nhnacademy.springboot.familyProject.domain.ResidentDto;
import com.nhnacademy.springboot.familyProject.domain.ResidentCreateRequest;
import com.nhnacademy.springboot.familyProject.domain.ResidentUpdateRequest;
import com.nhnacademy.springboot.familyProject.entity.Resident;
import com.nhnacademy.springboot.familyProject.exception.DataDuplicateException;
import com.nhnacademy.springboot.familyProject.exception.ResidentNotFoundException;
import com.nhnacademy.springboot.familyProject.repository.resident.ResidentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final PasswordEncoder passwordEncoder;

    public ResidentService(ResidentRepository residentRepository, PasswordEncoder passwordEncoder) {
        this.residentRepository = residentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResidentDto createResident(ResidentCreateRequest residentCreateRequest) {
        if(Objects.nonNull(residentRepository.findByResidentRegistrationNumber(residentCreateRequest.getResidentRegistrationNumber()))) {
            throw new DataDuplicateException(residentCreateRequest.getResidentRegistrationNumber());
        }
        if(Objects.nonNull(residentRepository.findById(residentCreateRequest.getId()))) {
            throw new DataDuplicateException(residentCreateRequest.getId());
        }

        Resident resident = Resident.builder()
                .name(residentCreateRequest.getName())
                .residentRegistrationNumber(residentCreateRequest.getResidentRegistrationNumber())
                .genderCode(residentCreateRequest.getGenderCode())
                .birthDate(residentCreateRequest.getBirthDate())
                .birthPlaceCode(residentCreateRequest.getBirthPlaceCode())
                .registrationBaseAddress(residentCreateRequest.getRegistrationBaseAddress())
                .id(residentCreateRequest.getId())
                .password(passwordEncoder.encode(residentCreateRequest.getPassword()))
                .email(residentCreateRequest.getEmail())
                .build();

        return ResidentDto.create(residentRepository.save(resident));
    }

    public ResidentDto updateResident(ResidentUpdateRequest residentUpdateRequest, Integer serialNumber) {
        Resident resident = residentRepository.findById(serialNumber)
                .orElseThrow(ResidentNotFoundException::new);
        String name;
        String id;
        String password;
        String email;

        if(Objects.isNull(residentUpdateRequest.getName())) {
            name = resident.getName();
        }else {
            name = residentUpdateRequest.getName();
        }
        if(Objects.isNull(residentUpdateRequest.getId())) {
            id = resident.getId();
        }else {
            id = residentUpdateRequest.getId();
        }
        if(Objects.isNull(residentUpdateRequest.getPassword())) {
            password = resident.getPassword();
        }else {
            password = passwordEncoder.encode(residentUpdateRequest.getPassword());
        }
        if(Objects.isNull(residentUpdateRequest.getEmail())) {
            email = resident.getEmail();
        }else {
            email = residentUpdateRequest.getEmail();
        }

        return ResidentDto.create(resident.update(name, id, password, email));
    }
}
