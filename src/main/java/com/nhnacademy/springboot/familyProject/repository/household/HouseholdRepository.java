package com.nhnacademy.springboot.familyProject.repository.household;

import com.nhnacademy.springboot.familyProject.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {
    Household findByResident_ResidentId(Integer serialNumber);
}
