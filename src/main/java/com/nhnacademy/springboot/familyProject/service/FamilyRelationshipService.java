package com.nhnacademy.springboot.familyProject.service;

import com.nhnacademy.springboot.familyProject.domain.*;
import com.nhnacademy.springboot.familyProject.entity.FamilyRelationship;
import com.nhnacademy.springboot.familyProject.exception.DataDuplicateException;
import com.nhnacademy.springboot.familyProject.exception.FamilyRelationshipNotFoundException;
import com.nhnacademy.springboot.familyProject.exception.ResidentNotFoundException;
import com.nhnacademy.springboot.familyProject.repository.familyRelationship.FamilyRelationshipRepository;
import com.nhnacademy.springboot.familyProject.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class FamilyRelationshipService {

    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;

    public FamilyRelationshipService(FamilyRelationshipRepository familyRelationshipRepository, ResidentRepository residentRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
        this.residentRepository = residentRepository;
    }

    @Transactional
    public FamilyRelationshipDto createFamilyRelationship(Integer serialNumber, FamilyRelationshipCreateRequest familyRelationshipCreateRequest) {
        residentRepository.findById(serialNumber)
                .orElseThrow(ResidentNotFoundException::new);
        residentRepository.findById(familyRelationshipCreateRequest.getFamilySerialNumber())
                .orElseThrow(ResidentNotFoundException::new);
        if(Objects.nonNull(familyRelationshipRepository.findByPk_BaseResidentSerialNumberAndPk_FamilyResidentRegistrationNumber
                (serialNumber, familyRelationshipCreateRequest.getFamilySerialNumber()))) {
            throw new DataDuplicateException("baseNumber and registrationNumber");
        }

        FamilyRelationship familyRelationship = FamilyRelationship.builder()
                .pk(new FamilyRelationship.Pk(familyRelationshipCreateRequest.getFamilySerialNumber(), serialNumber))
                .familyRelationshipCode(familyRelationshipCreateRequest.getRelationShip())
                .resident(residentRepository.findById(serialNumber).get())
                .build();

        return FamilyRelationshipDto.create(familyRelationshipRepository.save(familyRelationship));
    }

    @Transactional
    public FamilyRelationshipDto updateFamilyRelationship(Integer baseNumber, Integer registrationNumber, FamilyRelationshipUpdateRequest familyRelationshipUpdateRequest) {
        if(Objects.isNull(familyRelationshipRepository.findByPk_BaseResidentSerialNumberAndPk_FamilyResidentRegistrationNumber(baseNumber, registrationNumber))) {
            throw new FamilyRelationshipNotFoundException();
        }

        return FamilyRelationshipDto.create(
                familyRelationshipRepository.findByPk_BaseResidentSerialNumberAndPk_FamilyResidentRegistrationNumber(baseNumber, registrationNumber)
                        .update(familyRelationshipUpdateRequest.getRelationShip()));
    }

    @Transactional
    public void deleteFamilyRelationship(Integer baseNumber, Integer registrationNumber) {
        if(Objects.isNull(familyRelationshipRepository.findByPk_BaseResidentSerialNumberAndPk_FamilyResidentRegistrationNumber(baseNumber, registrationNumber))) {
            throw new FamilyRelationshipNotFoundException();
        }

        familyRelationshipRepository.delete(familyRelationshipRepository.findByPk_BaseResidentSerialNumberAndPk_FamilyResidentRegistrationNumber(baseNumber, registrationNumber));
    }

    public List<FamilyRelationResponse> readFamilyRelationship(String id) {
        if(Objects.isNull(residentRepository.findById(id))) {
            throw new ResidentNotFoundException();
        }

        return familyRelationshipRepository.findFamilyRelationshipById(id);
    }
}
