package com.nhnacademy.springboot.familyProject.repository.resident;

import com.nhnacademy.springboot.familyProject.entity.FamilyRelationship;
import com.nhnacademy.springboot.familyProject.entity.Resident;
import com.nhnacademy.springboot.familyProject.exception.ResidentNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ResidentRepositoryTest {

    @Autowired
    private ResidentRepository residentRepository;

    @Test
    @DisplayName("주민 고유번호로 주민 조회")
    void findById() {
        //given
        Integer id = 4;

        //when
        Resident resident = residentRepository.findById(id).orElseThrow(ResidentNotFoundException::new);

        //then
        Assertions.assertThat(resident.getResidentId()).isEqualTo(id);
    }

    @Test
    @DisplayName("주민 ID로 주민 조회")
    void findByIdentification() {
        //given
        String id = "resident4";

        //when
        Resident actual = residentRepository.findById(id);

        //then
        Assertions.assertThat(actual.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("주민등록번호로 주민 조회")
    void findByResidentRegistrationNumber() {
        //given
        String residentRegistrationNumber = "790510-1234564";

        //when
        Resident actual = residentRepository.findByResidentRegistrationNumber(residentRegistrationNumber);

        //then
        Assertions.assertThat(actual.getResidentRegistrationNumber()).isEqualTo(residentRegistrationNumber);
    }
}