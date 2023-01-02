package com.nhnacademy.springboot.familyProject.repository.birthDeathReportResident;

import com.nhnacademy.springboot.familyProject.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {
    BirthDeathReportResident findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumber(String code, Integer serialNumber);
    BirthDeathReportResident findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumberAndResident_ResidentId(String code, Integer targetSerialNumber, Integer reportSerialNumber);
}
