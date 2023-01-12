package com.nhnacademy.springboot.familyProject.familyRelationship.repository;

import com.nhnacademy.springboot.familyProject.familyRelationshipCertificateIssue.dto.FamilyRelationResponse;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface FamilyRelationshipRepositoryCustom {
    List<FamilyRelationResponse> findFamilyRelationshipById(String id);
}
