package com.nhnacademy.springboot.familyProject.repository.birthDeathReportResident;

import com.nhnacademy.springboot.familyProject.entity.BirthDeathReportResident;
import com.nhnacademy.springboot.familyProject.entity.QBirthDeathReportResident;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BirthDeathReportResidentRepositoryImpl extends QuerydslRepositorySupport implements BirthDeathReportResidentRepositoryCustom {
    public BirthDeathReportResidentRepositoryImpl() {
        super(BirthDeathReportResident.class);
    }

    /*
    select *
    from birth_death_report_resident
    where resident_serial_number = 8 and birth_death_type_code = "사망";
     */
    @Override
    public BirthDeathReportResident findBirthDeathReportResidentByPk(String code, Integer targetNumber) {
        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;
        return from(birthDeathReportResident)
                .where(birthDeathReportResident.pk.birthDeathTypeCode.eq(code)
                        .and(birthDeathReportResident.pk.residentSerialNumber.eq(targetNumber)))
                .select(birthDeathReportResident)
                .fetchOne();
    }
}
