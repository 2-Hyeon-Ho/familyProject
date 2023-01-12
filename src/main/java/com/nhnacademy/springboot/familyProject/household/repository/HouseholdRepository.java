package com.nhnacademy.springboot.familyProject.household.repository;

import com.nhnacademy.springboot.familyProject.household.domain.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {
    Household findByResident_ResidentId(Integer serialNumber);
}
