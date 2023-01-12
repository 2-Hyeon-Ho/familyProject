package com.nhnacademy.springboot.familyProject.birthDeathReportResident.service;

import com.nhnacademy.springboot.familyProject.birthDeathReportResident.dto.BirthReportRequest;
import com.nhnacademy.springboot.familyProject.birthDeathReportResident.dto.BirthReportResponse;
import com.nhnacademy.springboot.familyProject.birthDeathReportResident.domain.BirthDeathReportResident;
import com.nhnacademy.springboot.familyProject.resident.domain.Resident;
import com.nhnacademy.springboot.familyProject.exception.BirthDeathReportResidentNotFoundException;
import com.nhnacademy.springboot.familyProject.exception.DataDuplicateException;
import com.nhnacademy.springboot.familyProject.exception.ResidentNotFoundException;
import com.nhnacademy.springboot.familyProject.birthDeathReportResident.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.springboot.familyProject.resident.repository.ResidentRepository;
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
    public BirthReportResponse createBirthReport(Integer serialNumber, BirthReportRequest birthReportRequest) {
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

        return BirthReportResponse.create(birthDeathReportResidentRepository.save(birthReportResident));
    }

    @Transactional
    public BirthReportResponse updateBirthReport(Integer serialNumber,
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

        return BirthReportResponse.create(birthDeathReportResidentRepository.save(birthReport.birthReportUpdate(birthDeathReportDate, birthReportQualificationsCode, phoneNumber)));
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
