package com.nhnacademy.springboot.familyProject.householdMovementAddress.repository;

import com.nhnacademy.springboot.familyProject.householdMovementAddress.domain.HouseholdMovementAddress;
import com.nhnacademy.springboot.familyProject.entity.QHouseholdMovementAddress;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class HouseholdMovementAddressRepositoryImpl extends QuerydslRepositorySupport implements HouseholdMovementAddressRepositoryCustom {
    public HouseholdMovementAddressRepositoryImpl() {
        super(HouseholdMovementAddress.class);
    }

    /*
    select *
    from household_movement_address
    where household_serial_number = 1
    and house_movement_report_date = '2007-10-31';
     */
    @Override
    public HouseholdMovementAddress findHouseholdMovementAddressByPk(Integer householdSerialNumber, LocalDate houseMovementDate) {
        QHouseholdMovementAddress householdMovementAddress = QHouseholdMovementAddress.householdMovementAddress;

        return from(householdMovementAddress)
                .where(householdMovementAddress.pk.householdSerialNumber.eq(householdSerialNumber)
                        .and(householdMovementAddress.pk.houseMovementReportDate.eq(houseMovementDate)))
                .select(householdMovementAddress)
                .fetchOne();
    }
}
