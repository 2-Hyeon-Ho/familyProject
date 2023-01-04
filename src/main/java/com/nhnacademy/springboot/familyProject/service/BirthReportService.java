package com.nhnacademy.springboot.familyProject.service;

import com.nhnacademy.springboot.familyProject.domain.BirthReportRequest;
import com.nhnacademy.springboot.familyProject.domain.BirthReportDto;
import com.nhnacademy.springboot.familyProject.entity.BirthDeathReportResident;
import com.nhnacademy.springboot.familyProject.entity.Resident;
import com.nhnacademy.springboot.familyProject.exception.BirthDeathReportResidentNotFoundException;
import com.nhnacademy.springboot.familyProject.exception.DataDuplicateException;
import com.nhnacademy.springboot.familyProject.exception.ResidentNotFoundException;
import com.nhnacademy.springboot.familyProject.repository.birthDeathReportResident.BirthDeathReportResidentRepository;
import com.nhnacademy.springboot.familyProject.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class BirthReportService {
    private final String BIRTH_CODE = "출생";
    private final ResidentRepository residentRepository;
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;

    public BirthReportService(ResidentRepository residentRepository, BirthDeathReportResidentRepository birthDeathReportResidentRepository) {
        this.residentRepository = residentRepository;
        this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
    }

    @Transactional
    public BirthReportDto createBirthReport(Integer serialNumber, BirthReportRequest birthReportRequest) {
        Resident reportResident = residentRepository.findById(serialNumber)
                .orElseThrow(ResidentNotFoundException::new);
        residentRepository.findById(birthReportRequest.getResidentSerialNumber())
                .orElseThrow(ResidentNotFoundException::new);
        if(Objects.nonNull(birthDeathReportResidentRepository.findBirthDeathReportResidentByPk(BIRTH_CODE, birthReportRequest.getResidentSerialNumber()))) {
            throw new DataDuplicateException("BirthReport key value");
        }

        BirthDeathReportResident birthReportResident = BirthDeathReportResident.builder()
                .pk(new BirthDeathReportResident.Pk(BIRTH_CODE, birthReportRequest.getResidentSerialNumber()))
                .birthDeathReportDate(birthReportRequest.getBirthDeathReportDate())
                .birthReportQualificationsCode(birthReportRequest.getBirthReportQualificationsCode())
                .phoneNumber(birthReportRequest.getPhoneNumber())
                .resident(reportResident)
                .build();

        return BirthReportDto.create(birthDeathReportResidentRepository.save(birthReportResident));
    }

    @Transactional
    public BirthReportDto updateBirthReport(Integer serialNumber,
                                                  Integer targetSerialNumber,
                                                  BirthReportRequest birthReportRequest) {
        residentRepository.findById(serialNumber)
                .orElseThrow(ResidentNotFoundException::new);
        residentRepository.findById(targetSerialNumber)
                .orElseThrow(ResidentNotFoundException::new);

        if(Objects.isNull(birthDeathReportResidentRepository.findBirthDeathReportResidentByPk(BIRTH_CODE, targetSerialNumber))) {
            throw new BirthDeathReportResidentNotFoundException();
        }
        BirthDeathReportResident birthReport = birthDeathReportResidentRepository.findBirthDeathReportResidentByPk(BIRTH_CODE, targetSerialNumber);

        LocalDate birthDeathReportDate;
        String birthReportQualificationsCode;
        String phoneNumber;
        if(Objects.isNull(birthReportRequest.getBirthDeathReportDate())) {
            birthDeathReportDate = birthReport.getBirthDeathReportDate();
        }else {
            birthDeathReportDate = birthReportRequest.getBirthDeathReportDate();
        }
        if(Objects.isNull(birthReportRequest.getBirthReportQualificationsCode())) {
            birthReportQualificationsCode = birthReport.getBirthReportQualificationsCode();
        }else {
            birthReportQualificationsCode = birthReportRequest.getBirthReportQualificationsCode();
        }
        if(Objects.isNull(birthReportRequest.getPhoneNumber())) {
            phoneNumber = birthReport.getPhoneNumber();
        }else {
            phoneNumber = birthReportRequest.getPhoneNumber();
        }

        return BirthReportDto.create(birthDeathReportResidentRepository.save(birthReport.birthReportUpdate(birthDeathReportDate, birthReportQualificationsCode, phoneNumber)));
    }

    @Transactional
    public void deleteBirthReport(Integer serialNumber, Integer targetSerialNumber) {
        residentRepository.findById(serialNumber)
                .orElseThrow(ResidentNotFoundException::new);
        residentRepository.findById(targetSerialNumber)
                .orElseThrow(ResidentNotFoundException::new);

        BirthDeathReportResident birthReport = birthDeathReportResidentRepository.findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumberAndResident_ResidentId(BIRTH_CODE, targetSerialNumber, serialNumber);
        birthDeathReportResidentRepository.delete(birthReport);
    }
}
