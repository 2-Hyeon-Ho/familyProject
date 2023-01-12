package com.nhnacademy.springboot.familyProject.householdCompositionResident.repository;

import com.nhnacademy.springboot.familyProject.householdCompositionResident.domain.HouseholdCompositionResident;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface HouseholdCompositionResidentRepositoryCustom {
    List<HouseholdCompositionResident> findHouseholdCompositionResidentByHouseholdNumber(Integer householdSerialNumber);
}
