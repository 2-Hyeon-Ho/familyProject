package com.nhnacademy.springboot.familyProject.repository.household;

import com.nhnacademy.springboot.familyProject.household.domain.Household;
import com.nhnacademy.springboot.familyProject.exception.HouseholdNotFoundException;
import com.nhnacademy.springboot.familyProject.household.repository.HouseholdRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HouseholdRepositoryTest {

    @Autowired
    HouseholdRepository householdRepository;

    @Test
    @DisplayName("세대 고유번호로 세대 조회")
    void findById() {
        //given
        Integer householdSerialNumber = 1;

        //when
        Household actual = householdRepository.findById(householdSerialNumber)
                .orElseThrow(HouseholdNotFoundException::new);

        //then
        Assertions.assertThat(actual.getHouseholdSerialNumber()).isEqualTo(householdSerialNumber);
    }

    @Test
    @DisplayName("세대주 고유번호로 세대 조회")
    void findByResident_ResidentId() {
        //given
        Integer householdResidentSerialNumber = 4;

        //when
        Household actual = householdRepository.findByResident_ResidentId(householdResidentSerialNumber);

        //then
        Assertions.assertThat(actual.getResident().getResidentId()).isEqualTo(householdResidentSerialNumber);
    }
}