package com.nhnacademy.springboot.familyProject.repository.certificateIssue;

import com.nhnacademy.springboot.familyProject.entity.CertificateIssue;
import com.nhnacademy.springboot.familyProject.exception.ResidentNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CertificateIssueRepositoryTest {

    public final String FAMILY_RELATION = "가족관계증명서";
    public final String RESIDENT_REGISTRATION = "주민등록등본";

    @Autowired
    CertificateIssueRepository certificateIssueRepository;

    @Test
    @DisplayName("발행한 주민 ID로 증명서발급 조회")
    void findByResident_Id() {
        //given
        String id = "resident4";

        //when
        CertificateIssue familyRelationActual = certificateIssueRepository.findByResident_IdAndCertificateCode(id, FAMILY_RELATION)
                .orElseThrow(ResidentNotFoundException::new);
        CertificateIssue residentRegistrationActual = certificateIssueRepository.findByResident_IdAndCertificateCode(id, RESIDENT_REGISTRATION)
                .orElseThrow(ResidentNotFoundException::new);

        //then
        Assertions.assertThat(familyRelationActual.getResident().getId()).isEqualTo(id);
        Assertions.assertThat(residentRegistrationActual.getResident().getId()).isEqualTo(id);
    }
}