package com.nhnacademy.springboot.familyProject.repository.householdCompositionResident;

import com.nhnacademy.springboot.familyProject.householdCompositionResident.domain.HouseholdCompositionResident;
import com.nhnacademy.springboot.familyProject.householdCompositionResident.repository.HouseholdCompositionResidentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HouseholdCompositionResidentRepositoryTest {

    @Autowired
    HouseholdCompositionResidentRepository householdCompositionResidentRepository;

    @Test
    @DisplayName("세대고유번호로 세대구성주민 리스트 조회")
    void findHouseholdCompositionResidentByHouseholdNumber() {
        //given
        Integer householdSerialNumber = 1;

        //when
        List<HouseholdCompositionResident> actual = householdCompositionResidentRepository.findHouseholdCompositionResidentByHouseholdNumber(1);

        //then
        Assertions.assertThat(actual.size()).isEqualTo(4);
    }
}