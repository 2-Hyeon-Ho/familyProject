package com.nhnacademy.springboot.familyProject.repository.householdMovementAddress;

import com.nhnacademy.springboot.familyProject.entity.Household;
import com.nhnacademy.springboot.familyProject.entity.HouseholdMovementAddress;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HouseholdMovementAddressRepositoryTest {

    @Autowired
    HouseholdMovementAddressRepository householdMovementAddressRepository;

    @Test
    @DisplayName("세대 고유번호와 최종주소여부로 세대전입주소 조회")
    void findByLastAddressYnAndPk_HouseholdSerialNumber() {
        //given
        Integer householdSerialNumber = 1;
        String lastAddressYn = "Y";

        //when
        HouseholdMovementAddress actual = householdMovementAddressRepository.findByLastAddressYnAndPk_HouseholdSerialNumber(lastAddressYn, householdSerialNumber);

        //then
        Assertions.assertThat(actual.getLastAddressYn()).isEqualTo(lastAddressYn);
        Assertions.assertThat(actual.getPk().getHouseholdSerialNumber()).isEqualTo(householdSerialNumber);
    }

    @Test
    @DisplayName("전입신고일자와 세대 고유번호로 세대전입주소 조회")
    void findHouseholdMovementAddressByPk() {
        //given
        Integer householdSerialNumber = 1;
        LocalDate houseMovementReportDate = LocalDate.of(2007, 10, 31);
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk(houseMovementReportDate, householdSerialNumber);

        //when
        HouseholdMovementAddress actual = householdMovementAddressRepository.findHouseholdMovementAddressByPk(householdSerialNumber, houseMovementReportDate);

        //then
        Assertions.assertThat(actual.getPk()).isEqualTo(pk);
    }
}