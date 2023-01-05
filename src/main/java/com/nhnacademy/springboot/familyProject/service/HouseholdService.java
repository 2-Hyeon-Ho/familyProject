package com.nhnacademy.springboot.familyProject.service;

import com.nhnacademy.springboot.familyProject.domain.HouseholdCompositionResidentDto;
import com.nhnacademy.springboot.familyProject.domain.HouseholdDto;
import com.nhnacademy.springboot.familyProject.domain.HouseholdRequest;
import com.nhnacademy.springboot.familyProject.entity.Household;
import com.nhnacademy.springboot.familyProject.entity.HouseholdCompositionResident;
import com.nhnacademy.springboot.familyProject.entity.Resident;
import com.nhnacademy.springboot.familyProject.exception.DataDuplicateException;
import com.nhnacademy.springboot.familyProject.exception.HouseholdNotFoundException;
import com.nhnacademy.springboot.familyProject.exception.ResidentNotFoundException;
import com.nhnacademy.springboot.familyProject.repository.household.HouseholdRepository;
import com.nhnacademy.springboot.familyProject.repository.householdCompositionResident.HouseholdCompositionResidentRepository;
import com.nhnacademy.springboot.familyProject.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class HouseholdService {
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;

    public HouseholdService(HouseholdRepository householdRepository, ResidentRepository residentRepository, HouseholdCompositionResidentRepository householdCompositionResidentRepository) {
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
        this.householdCompositionResidentRepository = householdCompositionResidentRepository;
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
        householdRepository.save(household);

        List<HouseholdCompositionResidentDto> householdCompositionResidents = householdRequest.getHouseholdCompositionResidents();
        for (HouseholdCompositionResidentDto householdCompositionResident : householdCompositionResidents) {
            Resident householdResident = residentRepository.findById(householdCompositionResident.getResidentSerialNumber())
                    .orElseThrow(ResidentNotFoundException::new);
            HouseholdCompositionResident newHouseholdCompositionResident = HouseholdCompositionResident.builder()
                    .pk(new HouseholdCompositionResident.Pk(household.getHouseholdSerialNumber(), householdCompositionResident.getResidentSerialNumber()))
                    .reportDate(householdCompositionResident.getReportDate())
                    .householdRelationshipCode(householdCompositionResident.getHouseholdRelationshipCode())
                    .householdCompositionChangeReasonCode(householdCompositionResident.getHouseholdCompositionChangeReasonCode())
                    .houseHold(household)
                    .resident(householdResident)
                    .build();
            householdCompositionResidentRepository.save(newHouseholdCompositionResident);
        }

        household.update(householdCompositionResidentRepository.findHouseholdCompositionResidentByHouseholdNumber(household.getHouseholdSerialNumber()));

        return HouseholdDto.create(householdRepository.save(household), householdCompositionResidents);
    }

    @Transactional
    public void deleteHousehold(Integer householdSerialNumber) {
        Household household = householdRepository.findById(householdSerialNumber)
                .orElseThrow(HouseholdNotFoundException::new);
        List<HouseholdCompositionResident> householdCompositionResidents = householdCompositionResidentRepository.findHouseholdCompositionResidentByHouseholdNumber(householdSerialNumber);

        householdCompositionResidentRepository.deleteAll(householdCompositionResidents);
        householdRepository.delete(household);
    }
}
