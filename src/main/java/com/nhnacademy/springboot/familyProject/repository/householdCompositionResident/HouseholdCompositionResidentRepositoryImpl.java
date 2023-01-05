package com.nhnacademy.springboot.familyProject.repository.householdCompositionResident;

import com.nhnacademy.springboot.familyProject.entity.HouseholdCompositionResident;
import com.nhnacademy.springboot.familyProject.entity.QHouseholdCompositionResident;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HouseholdCompositionResidentRepositoryImpl extends QuerydslRepositorySupport implements HouseholdCompositionResidentRepositoryCustom {
    public HouseholdCompositionResidentRepositoryImpl() {
        super(HouseholdCompositionResident.class);
    }

    /*
    select *
    from household_composition_resident
    where household_serial_number = 1;
     */
    @Override
    public List<HouseholdCompositionResident> findHouseholdCompositionResidentByHouseholdNumber(Integer householdSerialNumber) {
        QHouseholdCompositionResident householdCompositionResident = QHouseholdCompositionResident.householdCompositionResident;

        return from(householdCompositionResident)
                .where(householdCompositionResident.houseHold.householdSerialNumber.eq(householdSerialNumber))
                .select(householdCompositionResident)
                .fetch();
    }
}
