package com.nhnacademy.springboot.familyProject.service;

import com.nhnacademy.springboot.familyProject.domain.HouseholdDto;
import com.nhnacademy.springboot.familyProject.domain.HouseholdRequest;
import com.nhnacademy.springboot.familyProject.entity.Household;
import com.nhnacademy.springboot.familyProject.entity.Resident;
import com.nhnacademy.springboot.familyProject.exception.DataDuplicateException;
import com.nhnacademy.springboot.familyProject.exception.HouseholdNotFoundException;
import com.nhnacademy.springboot.familyProject.exception.ResidentNotFoundException;
import com.nhnacademy.springboot.familyProject.repository.household.HouseholdRepository;
import com.nhnacademy.springboot.familyProject.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class HouseholdService {
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    public HouseholdService(HouseholdRepository householdRepository, ResidentRepository residentRepository) {
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
    }

    @Transactional
    public HouseholdDto createHousehold(HouseholdRequest householdRequest) {
        Resident resident = residentRepository.findById(householdRequest.getHouseholdResidentSerialNumber())
                .orElseThrow(ResidentNotFoundException::new);
        if(Objects.nonNull(householdRepository.findByResident_ResidentId(householdRequest.getHouseholdResidentSerialNumber()))) {
            throw new DataDuplicateException("householdResident");
        }

        Household household = Household.builder()
                .resident(resident)
                .householdCompositionDate(householdRequest.getHouseholdCompositionDate())
                .householdCompositionReasonCode(householdRequest.getHouseholdCompositionReasonCode())
                .currentHouseMovementAddress(householdRequest.getCurrentHouseMovementAddress())
                .build();

        return HouseholdDto.create(householdRepository.save(household));
    }

    @Transactional
    public void deleteHousehold(Integer householdSerialNumber) {
        Household household = householdRepository.findById(householdSerialNumber)
                .orElseThrow(HouseholdNotFoundException::new);

        householdRepository.delete(household);
    }
}
