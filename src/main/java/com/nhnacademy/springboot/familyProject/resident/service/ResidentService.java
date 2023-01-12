package com.nhnacademy.springboot.familyProject.resident.service;

import com.nhnacademy.springboot.familyProject.resident.dto.ResidentResponse;
import com.nhnacademy.springboot.familyProject.resident.dto.ResidentCreateRequest;
import com.nhnacademy.springboot.familyProject.resident.dto.ResidentUpdateRequest;
import com.nhnacademy.springboot.familyProject.resident.domain.Resident;
import com.nhnacademy.springboot.familyProject.exception.DataDuplicateException;
import com.nhnacademy.springboot.familyProject.exception.ResidentNotFoundException;
import com.nhnacademy.springboot.familyProject.resident.repository.ResidentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final PasswordEncoder passwordEncoder;

    public ResidentService(ResidentRepository residentRepository, PasswordEncoder passwordEncoder) {
        this.residentRepository = residentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResidentResponse createResident(ResidentCreateRequest residentCreateRequest) {
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

        return ResidentResponse.create(residentRepository.save(resident));
    }

    public ResidentResponse updateResident(ResidentUpdateRequest residentUpdateRequest, Integer serialNumber) {
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

        return ResidentResponse.create(residentRepository.save(resident.update(name, id, password, email)));
    }
}
