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

    private final CertificateIssueRepository certificateIssueRepository;

    public CertificateIssueService(CertificateIssueRepository certificateIssueRepository) {
        this.certificateIssueRepository = certificateIssueRepository;
    }

    public CertificateIssueDto readCertificateIssue(String id) {
        CertificateIssue certificateIssue = certificateIssueRepository.findByResident_Id(id)
                .orElseThrow(ResidentNotFoundException::new);

        return CertificateIssueDto.create(certificateIssue);
    }
}
