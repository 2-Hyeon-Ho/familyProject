package com.nhnacademy.springboot.familyProject.householdMovementAddress.service;

import com.nhnacademy.springboot.familyProject.householdMovementAddress.dto.HouseholdMovementAddressDto;
import com.nhnacademy.springboot.familyProject.householdMovementAddress.dto.HouseholdMovementAddressRequest;
import com.nhnacademy.springboot.familyProject.household.domain.Household;
import com.nhnacademy.springboot.familyProject.householdMovementAddress.domain.HouseholdMovementAddress;
import com.nhnacademy.springboot.familyProject.exception.DataDuplicateException;
import com.nhnacademy.springboot.familyProject.exception.HouseholdMovementAddressNotFoundException;
import com.nhnacademy.springboot.familyProject.exception.HouseholdNotFoundException;
import com.nhnacademy.springboot.familyProject.household.repository.HouseholdRepository;
import com.nhnacademy.springboot.familyProject.householdMovementAddress.repository.HouseholdMovementAddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class HouseholdMovementAddressService {
    public static final String YES = "Y";
    public static final String NO = "N";
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;
    private final HouseholdRepository householdRepository;

    public HouseholdMovementAddressService(HouseholdMovementAddressRepository householdMovementAddressRepository, HouseholdRepository householdRepository) {
        this.householdMovementAddressRepository = householdMovementAddressRepository;
        this.householdRepository = householdRepository;
    }

    @Transactional
    public HouseholdMovementAddressDto createHouseholdMovementAddress(Integer householdSerialNumber, HouseholdMovementAddressRequest householdMovementAddressRequest) {
        Household household = householdRepository.findById(householdSerialNumber)
                .orElseThrow(HouseholdNotFoundException::new);
        if(Objects.nonNull(householdMovementAddressRepository.findHouseholdMovementAddressByPk(householdSerialNumber, householdMovementAddressRequest.getHouseMovementReportDate()))) {
            throw new DataDuplicateException("householdMovement Primary key");
        }

        HouseholdMovementAddress householdMovementAddress = HouseholdMovementAddress.builder()
                .pk(new HouseholdMovementAddress.Pk(householdMovementAddressRequest.getHouseMovementReportDate(), householdSerialNumber))
                .houseMovementAddress(householdMovementAddressRequest.getHouseMovementAddress())
                .lastAddressYn(YES)
                .houseHold(household)
                .build();

        if(Objects.nonNull(householdMovementAddressRepository.findByLastAddressYnAndPk_HouseholdSerialNumber(YES, householdSerialNumber))) {
            householdMovementAddressRepository.save(householdMovementAddressRepository.findByLastAddressYnAndPk_HouseholdSerialNumber(YES, householdSerialNumber).updateLastAddressYn(NO));
        }

        return HouseholdMovementAddressDto.create(householdMovementAddressRepository.save(householdMovementAddress));
    }

    @Transactional
    public HouseholdMovementAddressDto updateHouseholdMovementAddress(Integer householdSerialNumber, LocalDate reportDate, HouseholdMovementAddressRequest householdMovementAddressRequest) {
        householdRepository.findById(householdSerialNumber)
                .orElseThrow(HouseholdNotFoundException::new);
        if(Objects.isNull(householdMovementAddressRepository.findHouseholdMovementAddressByPk(householdSerialNumber, reportDate))) {
            throw new HouseholdMovementAddressNotFoundException();
        }

        HouseholdMovementAddress householdMovementAddress =
                householdMovementAddressRepository.findHouseholdMovementAddressByPk(householdSerialNumber, reportDate);
        HouseholdMovementAddress newHouseholdMovementAddress =
                householdMovementAddress.update(householdMovementAddressRequest.getHouseMovementAddress());

        return HouseholdMovementAddressDto.create(householdMovementAddressRepository.save(newHouseholdMovementAddress));
    }

    @Transactional
    public void deleteHouseholdMovementAddress(Integer householdSerialNumber, LocalDate reportDate) {
        householdRepository.findById(householdSerialNumber)
                .orElseThrow(HouseholdNotFoundException::new);
        if(Objects.isNull(householdMovementAddressRepository.findHouseholdMovementAddressByPk(householdSerialNumber, reportDate))) {
            throw new HouseholdMovementAddressNotFoundException();
        }

        householdMovementAddressRepository.delete(householdMovementAddressRepository.findHouseholdMovementAddressByPk(householdSerialNumber, reportDate));
    }
}
