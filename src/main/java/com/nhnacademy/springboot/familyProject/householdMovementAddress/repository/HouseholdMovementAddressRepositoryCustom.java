package com.nhnacademy.springboot.familyProject.householdMovementAddress.repository;

import com.nhnacademy.springboot.familyProject.householdMovementAddress.domain.HouseholdMovementAddress;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;

@NoRepositoryBean
public interface HouseholdMovementAddressRepositoryCustom {
    HouseholdMovementAddress findHouseholdMovementAddressByPk(Integer householdSerialNumber, LocalDate houseMovementDate);
}
