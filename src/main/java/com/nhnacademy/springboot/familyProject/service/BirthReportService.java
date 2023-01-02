package com.nhnacademy.springboot.familyProject.service;

import com.nhnacademy.springboot.familyProject.domain.BirthRegistrationRequest;
import com.nhnacademy.springboot.familyProject.domain.BirthRegistrationDto;
import com.nhnacademy.springboot.familyProject.entity.BirthDeathReportResident;
import com.nhnacademy.springboot.familyProject.entity.Resident;
import com.nhnacademy.springboot.familyProject.exception.DataDuplicateException;
import com.nhnacademy.springboot.familyProject.exception.ResidentNotFoundException;
import com.nhnacademy.springboot.familyProject.repository.birthDeathReportResident.BirthDeathReportResidentRepository;
import com.nhnacademy.springboot.familyProject.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class BirthRegistrationService {
    private final String BIRTH_CODE = "출생";
    private final ResidentRepository residentRepository;
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;

    public BirthRegistrationService(ResidentRepository residentRepository, BirthDeathReportResidentRepository birthDeathReportResidentRepository) {
        this.residentRepository = residentRepository;
        this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
    }

    @Transactional
    public BirthRegistrationDto createBirthRegistration(Integer serialNumber,
                                                        BirthRegistrationRequest birthRegistrationRequest) {
        Resident reportResident = residentRepository.findById(serialNumber)
                .orElseThrow(ResidentNotFoundException::new);
        residentRepository.findById(birthRegistrationRequest.getResidentSerialNumber())
                .orElseThrow(ResidentNotFoundException::new);
        if(Objects.nonNull(birthDeathReportResidentRepository.findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumber(BIRTH_CODE, serialNumber))) {
            throw new DataDuplicateException("BirthRegistration key value");
        }

        BirthDeathReportResident birthDeathReportResident = BirthDeathReportResident.builder()
                .pk(new BirthDeathReportResident.Pk(BIRTH_CODE, serialNumber))
                .birthDeathReportDate(birthRegistrationRequest.getBirthDeathReportDate())
                .birthReportQualificationsCode(BIRTH_CODE)
                .phoneNumber(birthRegistrationRequest.getPhoneNumber())
                .resident(reportResident)
                .build();

        return BirthRegistrationDto.create(birthDeathReportResidentRepository.save(birthDeathReportResident));
    }

    @Transactional
    public BirthRegistrationDto updateBirthRegistration(Integer serialNumber,
                                                        Integer targetSerialNumber,
                                                        BirthRegistrationRequest birthRegistrationRequest) {
        residentRepository.findById(serialNumber)
                .orElseThrow(ResidentNotFoundException::new);
        residentRepository.findById(targetSerialNumber)
                .orElseThrow(ResidentNotFoundException::new);

        BirthDeathReportResident updateBirthDeathReportResident = birthDeathReportResidentRepository.findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumber(BIRTH_CODE, serialNumber)
                .update(birthRegistrationRequest);

        return BirthRegistrationDto.create(birthDeathReportResidentRepository.save(updateBirthDeathReportResident));
    }

    @Transactional
    public void deleteBirthRegistration(Integer serialNumber, Integer targetSerialNumber) {
        residentRepository.findById(serialNumber)
                .orElseThrow(ResidentNotFoundException::new);
        residentRepository.findById(targetSerialNumber)
                .orElseThrow(ResidentNotFoundException::new);

        BirthDeathReportResident birthReport = birthDeathReportResidentRepository.findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumberAndResident_ResidentId(BIRTH_CODE, targetSerialNumber, serialNumber);
        birthDeathReportResidentRepository.delete(birthReport);
    }
}
