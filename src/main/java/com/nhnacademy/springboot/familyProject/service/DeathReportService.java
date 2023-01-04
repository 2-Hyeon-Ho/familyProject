package com.nhnacademy.springboot.familyProject.service;

import com.nhnacademy.springboot.familyProject.domain.BirthReportRequest;
import com.nhnacademy.springboot.familyProject.domain.DeathReportDto;
import com.nhnacademy.springboot.familyProject.domain.DeathReportRequest;
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
public class DeathReportService {
    private final String DEATH_CODE = "사망";
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final ResidentRepository residentRepository;

    public DeathReportService(BirthDeathReportResidentRepository birthDeathReportResidentRepository, ResidentRepository residentRepository) {
        this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
        this.residentRepository = residentRepository;
    }

    @Transactional
    public DeathReportDto createDeathReport(Integer serialNumber, DeathReportRequest deathReportRequest) {
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

        return DeathReportDto.create(birthDeathReportResidentRepository.save(deathReportResident));
    }

    @Transactional
    public DeathReportDto updateDeathReport(Integer serialNumber, Integer targetSerialNumber, DeathReportRequest deathReportRequest) {
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

        return DeathReportDto.create(birthDeathReportResidentRepository.save(deathReport.deathReportUpdate(birthDeathReportDate, deathReportQualificationsCode, phoneNumber)));
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
