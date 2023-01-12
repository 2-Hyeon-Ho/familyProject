package com.nhnacademy.springboot.familyProject.birthDeathReportResident.repository;

import com.nhnacademy.springboot.familyProject.birthDeathReportResident.domain.BirthDeathReportResident;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BirthDeathReportResidentRepositoryCustom {
    BirthDeathReportResident findBirthDeathReportResidentByPk(String code, Integer targetNumber);
}
