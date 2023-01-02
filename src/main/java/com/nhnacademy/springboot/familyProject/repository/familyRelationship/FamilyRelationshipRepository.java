package com.nhnacademy.springboot.familyProject.repository.familyRelationship;

import com.nhnacademy.springboot.familyProject.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk>, FamilyRelationshipRepositoryCustom {
    FamilyRelationship findByPk_BaseResidentSerialNumberAndPk_FamilyResidentRegistrationNumber(Integer baseNumber, Integer registrationNumber);
    FamilyRelationship findByPk_BaseResidentSerialNumberAndFamilyRelationshipCode(Integer baseNumber, String relationshipCode);
}
