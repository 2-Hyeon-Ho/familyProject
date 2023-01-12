package com.nhnacademy.springboot.familyProject.certificateIssue.repository;

import com.nhnacademy.springboot.familyProject.certificateIssue.domain.CertificateIssue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
    Optional<CertificateIssue> findByResident_IdAndCertificateCode(String id, String certificateCode);
}
