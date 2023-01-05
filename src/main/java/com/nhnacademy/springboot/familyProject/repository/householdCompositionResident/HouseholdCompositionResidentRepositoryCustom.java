package com.nhnacademy.springboot.familyProject.repository.householdCompositionResident;

import com.nhnacademy.springboot.familyProject.entity.HouseholdCompositionResident;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface HouseholdCompositionResidentRepositoryCustom {
    List<HouseholdCompositionResident> findHouseholdCompositionResidentByHouseholdNumber(Integer householdSerialNumber);
}
