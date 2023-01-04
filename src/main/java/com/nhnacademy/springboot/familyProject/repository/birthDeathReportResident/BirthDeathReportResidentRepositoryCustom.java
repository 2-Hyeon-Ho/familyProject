package com.nhnacademy.springboot.familyProject.repository.birthDeathReportResident;

import com.nhnacademy.springboot.familyProject.entity.BirthDeathReportResident;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BirthDeathReportResidentRepositoryCustom {
    BirthDeathReportResident findBirthDeathReportResidentByPk(String code, Integer targetNumber);
}
