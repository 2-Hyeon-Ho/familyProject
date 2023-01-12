package com.nhnacademy.springboot.familyProject.familyRelationshipCertificateIssue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class FamilyRelationResponse {
    private String familyRelationshipCode;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime birthDate;
    private String residentRegistrationNumber;
    private String genderCode;
}
