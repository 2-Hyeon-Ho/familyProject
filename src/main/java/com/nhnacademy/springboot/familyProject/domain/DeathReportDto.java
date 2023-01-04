package com.nhnacademy.springboot.familyProject.domain;

import com.nhnacademy.springboot.familyProject.entity.BirthDeathReportResident;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DeathReportDto {
    private Integer residentSerialNumber;
    private String birthDeathTypeCode;
    private LocalDate birthDeathReportDate;
    private Integer reportResidentSerialNumber;
    private String deathReportQualificationsCode;
    private String phoneNumber;

    public static DeathReportDto create(BirthDeathReportResident birthDeathReportResident) {
        return new DeathReportDto(
                birthDeathReportResident.getPk().getResidentSerialNumber(),
                birthDeathReportResident.getPk().getBirthDeathTypeCode(),
                birthDeathReportResident.getBirthDeathReportDate(),
                birthDeathReportResident.getResident().getResidentId(),
                birthDeathReportResident.getDeathReportQualificationsCode(),
                birthDeathReportResident.getPhoneNumber()
        );
    }
}
