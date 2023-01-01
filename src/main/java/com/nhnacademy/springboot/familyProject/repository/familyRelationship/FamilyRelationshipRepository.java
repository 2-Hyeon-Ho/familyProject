package com.nhnacademy.springboot.familyProject.repository.familyRelationship;

import com.nhnacademy.springboot.familyProject.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk>, FamilyRelationshipRepositoryCustom {
}
