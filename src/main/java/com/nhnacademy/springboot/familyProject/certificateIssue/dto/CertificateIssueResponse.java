package com.nhnacademy.springboot.familyProject.certificateIssue.dto;

import com.nhnacademy.springboot.familyProject.certificateIssue.domain.CertificateIssue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CertificateIssueResponse {
    private String certificateCode;
    private LocalDateTime certificateIssueDate;

    public static CertificateIssueResponse create(CertificateIssue certificateIssue) {
        return new CertificateIssueResponse(
                certificateIssue.getCertificateCode(),
                certificateIssue.getCertificateIssueDate()
        );
    }
}
