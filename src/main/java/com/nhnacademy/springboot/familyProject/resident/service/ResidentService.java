package com.nhnacademy.springboot.familyProject.resident.service;

import com.nhnacademy.springboot.familyProject.common.constant.ErrorCode;
import com.nhnacademy.springboot.familyProject.exception.CustomException;
import com.nhnacademy.springboot.familyProject.resident.domain.DeathPlaceCode;
import com.nhnacademy.springboot.familyProject.resident.domain.Email;
import com.nhnacademy.springboot.familyProject.resident.domain.Identification;
import com.nhnacademy.springboot.familyProject.resident.domain.Name;
import com.nhnacademy.springboot.familyProject.resident.domain.Password;
import com.nhnacademy.springboot.familyProject.resident.domain.ResidentRegistrationNumber;
import com.nhnacademy.springboot.familyProject.resident.dto.ResidentResponse;
import com.nhnacademy.springboot.familyProject.resident.dto.ResidentCreateRequest;
import com.nhnacademy.springboot.familyProject.resident.dto.ResidentUpdateDeathRequest;
import com.nhnacademy.springboot.familyProject.resident.dto.ResidentUpdateRequest;
import com.nhnacademy.springboot.familyProject.resident.domain.Resident;
import com.nhnacademy.springboot.familyProject.exception.DataDuplicateException;
import com.nhnacademy.springboot.familyProject.exception.ResidentNotFoundException;
import com.nhnacademy.springboot.familyProject.resident.repository.ResidentRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final PasswordEncoder passwordEncoder;

    public ResidentService(ResidentRepository residentRepository, PasswordEncoder passwordEncoder) {
        this.residentRepository = residentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ResidentResponse createResident(ResidentCreateRequest residentCreateRequest) {
        if(Objects.nonNull(residentRepository.findByResidentRegistrationNumber(new ResidentRegistrationNumber(residentCreateRequest.getResidentRegistrationNumber())))) {
            throw new DataDuplicateException(residentCreateRequest.getResidentRegistrationNumber());
        }
        if(Objects.nonNull(residentRepository.findById(new Identification(residentCreateRequest.getId())))) {
            throw new DataDuplicateException(residentCreateRequest.getId());
        }

        Resident resident = residentRepository.save(residentCreateRequest.toResident(passwordEncoder));

        return new ResidentResponse(resident);
    }

    public List<ResidentResponse> readAllResident(Pageable pageable) {
        Page<Resident> residents = residentRepository.findAll(pageable);

        return residents.get()
            .map(ResidentResponse::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public ResidentResponse updateResident(Integer serialNumber, ResidentUpdateRequest residentUpdateRequest) {
        Resident resident = residentFindById(serialNumber);

        if(Objects.nonNull(residentRepository.findById(new Identification(residentUpdateRequest.getId())))) {
            throw new DataDuplicateException(residentUpdateRequest.getId());
        }
        resident.update(
            new Name(residentUpdateRequest.getName()),
            new Identification(residentUpdateRequest.getId()),
            new Password(residentUpdateRequest.getPassword(), passwordEncoder),
            new Email(residentUpdateRequest.getEmail())
        );
        return new ResidentResponse(resident);
    }

    @Transactional
    public ResidentResponse updateDeathResident(Integer serialNumber, ResidentUpdateDeathRequest residentUpdateDeathRequest) {
        Resident resident = residentFindById(serialNumber);

        resident.updateDeath(
            residentUpdateDeathRequest.getDeathDate(),
            DeathPlaceCode.valueOf(residentUpdateDeathRequest.getDeathPlaceCode()),
            residentUpdateDeathRequest.getDeathPlaceAddress()
        );
        return new ResidentResponse(resident);
    }

    @Transactional
    public ResidentResponse deleteResident(Integer serialNumber) {
        Resident resident = residentFindById(serialNumber);
        residentRepository.delete(resident);

        return new ResidentResponse(resident);
    }

    private Resident residentFindById(Integer serialNumber) {
        Optional<Resident> resident = residentRepository.findById(serialNumber);
        if(resident.isPresent()) {
            return resident.get();
        }
        throw new CustomException(ErrorCode.RESIDENT_NOT_FOUND);
    }
}
