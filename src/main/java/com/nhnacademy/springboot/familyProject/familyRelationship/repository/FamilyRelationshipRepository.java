package com.nhnacademy.springboot.familyProject.familyRelationship.repository;

import com.nhnacademy.springboot.familyProject.familyRelationship.domain.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk>, FamilyRelationshipRepositoryCustom {
    FamilyRelationship findByPk_BaseResidentSerialNumberAndPk_FamilyResidentRegistrationNumber(Integer baseNumber, Integer registrationNumber);
}
