package com.nhnacademy.springboot.familyProject.service;

import com.nhnacademy.springboot.familyProject.domain.CertificateIssueDto;
import com.nhnacademy.springboot.familyProject.entity.CertificateIssue;
import com.nhnacademy.springboot.familyProject.exception.ResidentNotFoundException;
import com.nhnacademy.springboot.familyProject.repository.certificateIssue.CertificateIssueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CertificateIssueService {

    public final String FAMILY_RELATION = "가족관계증명서";
    public final String RESIDENT_REGISTRATION = "주민등록등본";
    private final CertificateIssueRepository certificateIssueRepository;

    public CertificateIssueService(CertificateIssueRepository certificateIssueRepository) {
        this.certificateIssueRepository = certificateIssueRepository;
    }

    public CertificateIssueDto readFamilyRelationCertificateIssue(String id) {
        CertificateIssue certificateIssue = certificateIssueRepository.findByResident_IdAndCertificateCode(id, FAMILY_RELATION)
                .orElseThrow(ResidentNotFoundException::new);

        return CertificateIssueDto.create(certificateIssue);
    }

    public CertificateIssueDto readResidentRegistrationCertificateIssue(String id) {
        CertificateIssue certificateIssue = certificateIssueRepository.findByResident_IdAndCertificateCode(id, RESIDENT_REGISTRATION)
                .orElseThrow(ResidentNotFoundException::new);

        return CertificateIssueDto.create(certificateIssue);
    }
}
