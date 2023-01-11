package com.nhnacademy.springboot.familyProject.repository.birthDeathReportResident;

import com.nhnacademy.springboot.familyProject.entity.BirthDeathReportResident;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BirthDeathReportResidentRepositoryTest {

    @Autowired
    BirthDeathReportResidentRepository birthDeathReportResidentRepository;

    @Test
    @DisplayName("출생,사망 유형코드와 피신고자 고유번호, 신고자 고유번호로 출생사망신고주민 조회")
    void findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumberAndResident_ResidentId() {
        //given
        String typeCode = "출생";
        Integer targetResidentSerialNumber = 7;
        Integer reportResidentSerialNumber = 4;

        //when
        BirthDeathReportResident actual = birthDeathReportResidentRepository.findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumberAndResident_ResidentId(typeCode, targetResidentSerialNumber, reportResidentSerialNumber);

        //then
        Assertions.assertThat(actual.getPk().getBirthDeathTypeCode()).isEqualTo(typeCode);
        Assertions.assertThat(actual.getPk().getResidentSerialNumber()).isEqualTo(targetResidentSerialNumber);
        Assertions.assertThat(actual.getResident().getResidentId()).isEqualTo(reportResidentSerialNumber);
    }

    @Test
    @DisplayName("출생, 사망 유형코드와 피신고자 고유번호로 출생사망신고주민 조회")
    void findBirthDeathReportResidentByPk() {
        //given
        String typeCode = "출생";
        Integer targetResidentSerialNumber = 7;
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(typeCode, targetResidentSerialNumber);

        //when
        BirthDeathReportResident actual = birthDeathReportResidentRepository.findBirthDeathReportResidentByPk(typeCode, targetResidentSerialNumber);

        //then
        Assertions.assertThat(actual.getPk()).isEqualTo(pk);
    }
}