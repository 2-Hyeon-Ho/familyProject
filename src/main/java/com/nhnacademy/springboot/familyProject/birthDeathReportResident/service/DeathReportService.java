package com.nhnacademy.springboot.familyProject.birthDeathReportResident.service;

import com.nhnacademy.springboot.familyProject.birthDeathReportResident.dto.DeathReportResponse;
import com.nhnacademy.springboot.familyProject.birthDeathReportResident.dto.DeathReportRequest;
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
public class DeathReportService {
    private final String DEATH_CODE = "사망";
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final ResidentRepository residentRepository;

    public DeathReportService(BirthDeathReportResidentRepository birthDeathReportResidentRepository, ResidentRepository residentRepository) {
        this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
        this.residentRepository = residentRepository;
    }

    @Transactional
    public DeathReportResponse createDeathReport(Integer serialNumber, DeathReportRequest deathReportRequest) {
        Resident reportResident = residentRepository.findById(serialNumber)
                .orElseThrow(ResidentNotFoundException::new);
        residentRepository.findById(deathReportRequest.getResidentSerialNumber())
                .orElseThrow(ResidentNotFoundException::new);
        if(Objects.nonNull(birthDeathReportResidentRepository.findBirthDeathReportResidentByPk(DEATH_CODE,deathReportRequest.getResidentSerialNumber()))) {
            throw new DataDuplicateException("DeathReport key value");
        }

        BirthDeathReportResident deathReportResident = BirthDeathReportResident.builder()
                .pk(new BirthDeathReportResident.Pk(DEATH_CODE, deathReportRequest.getResidentSerialNumber()))
                .birthDeathReportDate(deathReportRequest.getBirthDeathReportDate())
                .deathReportQualificationsCode(deathReportRequest.getDeathReportQualificationsCode())
                .phoneNumber(deathReportRequest.getPhoneNumber())
                .resident(reportResident)
                .build();

        return DeathReportResponse.create(birthDeathReportResidentRepository.save(deathReportResident));
    }

    @Transactional
    public DeathReportResponse updateDeathReport(Integer serialNumber, Integer targetSerialNumber, DeathReportRequest deathReportRequest) {
        residentRepository.findById(serialNumber)
                .orElseThrow(ResidentNotFoundException::new);
        residentRepository.findById(targetSerialNumber)
                .orElseThrow(ResidentNotFoundException::new);

        if(Objects.isNull(birthDeathReportResidentRepository.findBirthDeathReportResidentByPk(DEATH_CODE, targetSerialNumber))) {
            throw new BirthDeathReportResidentNotFoundException();
        }
        BirthDeathReportResident deathReport = birthDeathReportResidentRepository.findBirthDeathReportResidentByPk(DEATH_CODE, targetSerialNumber);

        LocalDate birthDeathReportDate;
        String deathReportQualificationsCode;
        String phoneNumber;
        if(Objects.isNull(deathReportRequest.getBirthDeathReportDate())) {
            birthDeathReportDate = deathReport.getBirthDeathReportDate();
        }else {
            birthDeathReportDate = deathReportRequest.getBirthDeathReportDate();
        }
        if(Objects.isNull(deathReportRequest.getDeathReportQualificationsCode())) {
            deathReportQualificationsCode = deathReport.getDeathReportQualificationsCode();
        }else {
            deathReportQualificationsCode = deathReportRequest.getDeathReportQualificationsCode();
        }
        if(Objects.isNull(deathReportRequest.getPhoneNumber())) {
            phoneNumber = deathReport.getPhoneNumber();
        }else {
            phoneNumber = deathReportRequest.getPhoneNumber();
        }

        return DeathReportResponse.create(birthDeathReportResidentRepository.save(deathReport.deathReportUpdate(birthDeathReportDate, deathReportQualificationsCode, phoneNumber)));
    }

    @Transactional
    public void deleteDeathReport(Integer serialNumber, Integer targetSerialNumber) {
        residentRepository.findById(serialNumber)
                .orElseThrow(ResidentNotFoundException::new);
        residentRepository.findById(targetSerialNumber)
                .orElseThrow(ResidentNotFoundException::new);

        BirthDeathReportResident deathReport = birthDeathReportResidentRepository.findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumberAndResident_ResidentId(DEATH_CODE, targetSerialNumber, serialNumber);
        birthDeathReportResidentRepository.delete(deathReport);
    }
}
