package com.nhnacademy.springboot.familyProject.resident.repository;


import com.nhnacademy.springboot.familyProject.common.constant.ErrorCode;
import com.nhnacademy.springboot.familyProject.exception.CustomException;
import com.nhnacademy.springboot.familyProject.resident.domain.Identification;
import com.nhnacademy.springboot.familyProject.resident.domain.Resident;
import com.nhnacademy.springboot.familyProject.resident.domain.ResidentRegistrationNumber;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
        Optional<Resident> resident = residentRepository.findById(id);
        if(resident.isEmpty()) {
            throw new CustomException(ErrorCode.RESIDENT_NOT_FOUND);
        }

        //then
        Assertions.assertThat(resident.get().getResidentId()).isEqualTo(id);
    }

    @Test
    @DisplayName("주민 ID로 주민 조회")
    void findByIdentification() {
        //given
        Identification id = new Identification("resident4");

        //when
        Resident actual = residentRepository.findById(id);

        //then
        Assertions.assertThat(actual.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("주민등록번호로 주민 조회")
    void findByResidentRegistrationNumber() {
        //given
        ResidentRegistrationNumber residentRegistrationNumber = new ResidentRegistrationNumber("790510-1234564");

        //when
        Resident actual = residentRepository.findByResidentRegistrationNumber(residentRegistrationNumber);

        //then
        Assertions.assertThat(actual.getResidentRegistrationNumber()).isEqualTo(residentRegistrationNumber);
    }

    @Test
    @DisplayName("주민전체 조회")
    void findAll() {
        //given
        int page = 0;
        int size = 10;

        //when
        Page<Resident> actual = residentRepository.findAll(PageRequest.of(page, size));

        //then
        Assertions.assertThat(actual.getContent().size()).isEqualTo(actual.getTotalElements());
    }
}