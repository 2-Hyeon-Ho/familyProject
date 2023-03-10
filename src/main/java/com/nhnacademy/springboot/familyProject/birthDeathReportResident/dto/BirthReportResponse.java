package com.nhnacademy.springboot.familyProject.birthDeathReportResident.dto;

import com.nhnacademy.springboot.familyProject.birthDeathReportResident.domain.BirthDeathReportResident;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BirthReportResponse {
    private Integer residentSerialNumber;
    private String birthDeathTypeCode;
    private Integer reportResidentSerialNumber;
    private LocalDate birthDeathReportDate;
    private String birthReportQualificationsCode;
    private String phoneNumber;

    public static BirthReportResponse create(BirthDeathReportResident birthDeathReportResident) {
        return new BirthReportResponse(
                birthDeathReportResident.getPk().getResidentSerialNumber(),
                birthDeathReportResident.getPk().getBirthDeathTypeCode(),
                birthDeathReportResident.getResident().getResidentId(),
                birthDeathReportResident.getBirthDeathReportDate(),
                birthDeathReportResident.getBirthReportQualificationsCode(),
                birthDeathReportResident.getPhoneNumber()
                );
    }

//    private String targetName;
//    private String genderCode;
//    private LocalDateTime birthDate;
//    private String birthPlaceCode;
//    private String registrationBaseAddress;
//    private String fatherName;
//    private String fatherRegistrationNumber;
//    private String motherName;
//    private String motherRegistrationNumber;
//    private String reportResidentName;
//    private String reportResidentRegistrationNumber;
//    private String birthReportQualificationsCode;
//    private String email;
//    private String phoneNumber;

//    public static BirthRegistrationDto create(Resident targetResident, Resident father, Resident mother, Resident reportResident) {
//        return new BirthRegistrationDto(
//                targetResident.getName(),
//                targetResident.getGenderCode(),
//                targetResident.getBirthDate(),
//                targetResident.getBirthPlaceCode(),
//                targetResident.getRegistrationBaseAddress(),
//                father.getName(),
//                father.getResidentRegistrationNumber(),
//                mother.getName(),
//                mother.getResidentRegistrationNumber(),
//                reportResident.getName(),
//                reportResident.getResidentRegistrationNumber(),
//
//        )
//    }
}
