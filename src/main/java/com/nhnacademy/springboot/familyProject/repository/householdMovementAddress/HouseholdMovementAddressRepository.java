package com.nhnacademy.springboot.familyProject.repository.householdMovementAddress;

import com.nhnacademy.springboot.familyProject.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {
}
