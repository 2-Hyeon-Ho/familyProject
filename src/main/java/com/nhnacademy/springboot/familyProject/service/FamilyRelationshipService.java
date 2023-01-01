package com.nhnacademy.springboot.familyProject.service;

import com.nhnacademy.springboot.familyProject.domain.CertificateIssueDto;
import com.nhnacademy.springboot.familyProject.domain.ResidentDto;
import com.nhnacademy.springboot.familyProject.entity.Resident;
import com.nhnacademy.springboot.familyProject.exception.ResidentNotFoundException;
import com.nhnacademy.springboot.familyProject.repository.familyRelationship.FamilyRelationshipRepository;
import com.nhnacademy.springboot.familyProject.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FamilyRelationshipService {

    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;

    public FamilyRelationshipService(FamilyRelationshipRepository familyRelationshipRepository, ResidentRepository residentRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
        this.residentRepository = residentRepository;
    }

//    public List<ResidentDto> readFamilyRelationship(String id) {
//        residentRepository.findById(id)
//                .orElseThrow(ResidentNotFoundException::new);
//
//        return familyRelationshipRepository.findFamilyRelationshipById(id);
//    }
}
