package com.nhnacademy.springboot.familyProject.repository.familyRelationship;

import com.nhnacademy.springboot.familyProject.domain.FamilyRelationResponse;
import com.nhnacademy.springboot.familyProject.domain.FamilyRelationshipDto;
import com.nhnacademy.springboot.familyProject.domain.ResidentDto;
import com.nhnacademy.springboot.familyProject.entity.FamilyRelationship;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FamilyRelationshipRepositoryTest {

    @Autowired
    FamilyRelationshipRepository familyRelationshipRepository;

    @Test
    @DisplayName("기준주민 고유번호와 가족주민 고유번호로 가족관계 조회")
    void findByPk_BaseResidentSerialNumberAndPk_FamilyResidentRegistrationNumber() {
        //given
        Integer baseResidentSerialNumber = 4;
        Integer familyResidentSerialNumber = 5;

        //when
        FamilyRelationship actual = familyRelationshipRepository.findByPk_BaseResidentSerialNumberAndPk_FamilyResidentRegistrationNumber(baseResidentSerialNumber, familyResidentSerialNumber);

        //then
        Assertions.assertThat(actual.getPk().getBaseResidentSerialNumber()).isEqualTo(baseResidentSerialNumber);
        Assertions.assertThat(actual.getPk().getFamilyResidentRegistrationNumber()).isEqualTo(familyResidentSerialNumber);
    }

    @Test
    @DisplayName("주민 ID로 가족리스트 조회")
    void findFamilyRelationshipById() {
        //given
        String id = "resident4";

        //when
        List<FamilyRelationResponse> actual = familyRelationshipRepository.findFamilyRelationshipById(id);

        //then
        Assertions.assertThat(actual.size()).isEqualTo(4);
    }
}