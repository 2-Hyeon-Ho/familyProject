package com.nhnacademy.springboot.familyProject.repository.familyRelationship;

import com.nhnacademy.springboot.familyProject.domain.FamilyRelationResponse;
import com.nhnacademy.springboot.familyProject.domain.ResidentDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface FamilyRelationshipRepositoryCustom {
    List<FamilyRelationResponse> findFamilyRelationshipById(String id);
}
