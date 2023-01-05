package com.nhnacademy.springboot.familyProject.repository.householdMovementAddress;

import com.nhnacademy.springboot.familyProject.entity.HouseholdMovementAddress;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;

@NoRepositoryBean
public interface HouseholdMovementAddressRepositoryCustom {
    HouseholdMovementAddress findHouseholdMovementAddressByPk(Integer householdSerialNumber, LocalDate houseMovementDate);
}
