package com.nhnacademy.springboot.familyProject.domain;

import com.nhnacademy.springboot.familyProject.entity.CertificateIssue;
import com.nhnacademy.springboot.familyProject.entity.Resident;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CertificateIssueDto {
    private String certificateCode;
    private LocalDateTime certificateIssueDate;

    public static CertificateIssueDto create(CertificateIssue certificateIssue) {
        return new CertificateIssueDto(
                certificateIssue.getCertificateCode(),
                certificateIssue.getCertificateIssueDate()
        );
    }
}
