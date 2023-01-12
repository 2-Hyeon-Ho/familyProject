package com.nhnacademy.springboot.familyProject.birthDeathReportResident.dto;

import com.nhnacademy.springboot.familyProject.birthDeathReportResident.domain.BirthDeathReportResident;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DeathReportResponse {
    private Integer residentSerialNumber;
    private String birthDeathTypeCode;
    private LocalDate birthDeathReportDate;
    private Integer reportResidentSerialNumber;
    private String deathReportQualificationsCode;
    private String phoneNumber;

    public static DeathReportResponse create(BirthDeathReportResident birthDeathReportResident) {
        return new DeathReportResponse(
                birthDeathReportResident.getPk().getResidentSerialNumber(),
                birthDeathReportResident.getPk().getBirthDeathTypeCode(),
                birthDeathReportResident.getBirthDeathReportDate(),
                birthDeathReportResident.getResident().getResidentId(),
                birthDeathReportResident.getDeathReportQualificationsCode(),
                birthDeathReportResident.getPhoneNumber()
        );
    }
}
