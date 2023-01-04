package com.nhnacademy.springboot.familyProject.repository.birthDeathReportResident;

import com.nhnacademy.springboot.familyProject.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk>, BirthDeathReportResidentRepositoryCustom {
    BirthDeathReportResident findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumberAndResident_ResidentId(String code, Integer targetSerialNumber, Integer reportSerialNumber);
}
