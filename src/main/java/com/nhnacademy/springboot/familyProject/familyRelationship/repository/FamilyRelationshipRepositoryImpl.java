package com.nhnacademy.springboot.familyProject.familyRelationship.repository;

import com.nhnacademy.springboot.familyProject.familyRelationshipCertificateIssue.dto.FamilyRelationResponse;
import com.nhnacademy.springboot.familyProject.familyRelationship.domain.FamilyRelationship;
import com.nhnacademy.springboot.familyProject.entity.QFamilyRelationship;
import com.nhnacademy.springboot.familyProject.entity.QResident;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FamilyRelationshipRepositoryImpl extends QuerydslRepositorySupport implements FamilyRelationshipRepositoryCustom {

    public FamilyRelationshipRepositoryImpl() {
        super(FamilyRelationship.class);
    }

    /*
    select  fr.family_relationship_code as '구분',
			r.name as '성명',
			r.birth_date as '출생연월일',
            r.resident_registration_number as '주민등록번호',
            r.gender_code as '성별'
	from family_relationship as fr
	inner join resident as r
		on fr.family_resident_serial_number = r.resident_serial_number
	left join resident as rr
		on fr.base_resident_serial_number = rr.resident_serial_number
    where rr.resident_serial_number =4
     */

    @Override
    public List<FamilyRelationResponse> findFamilyRelationshipById(String id) {
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;
        QResident resident = QResident.resident;
        QResident subResident = new QResident("sub");
        return from(familyRelationship)
                .innerJoin(familyRelationship.resident, resident)
                .innerJoin(familyRelationship.resident, subResident)
                .where(resident.residentId.eq(getResidentIdById(id)))
                .select(Projections.constructor(
                        FamilyRelationResponse.class,
                        familyRelationship.familyRelationshipCode,
                        subResident.name,
                        subResident.birthDate,
                        subResident.residentRegistrationNumber,
                        subResident.genderCode
                ))
                .fetch();
    }

    public Integer getResidentIdById(String id) {
        QResident resident = QResident.resident;

        return from(resident)
                .where(resident.id.eq(id))
                .select(resident.residentId)
                .fetchOne();
    }
}
