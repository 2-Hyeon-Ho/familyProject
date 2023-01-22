package com.nhnacademy.springboot.familyProject.resident.repository;

import com.nhnacademy.springboot.familyProject.resident.domain.Identification;
import com.nhnacademy.springboot.familyProject.resident.domain.Resident;
import com.nhnacademy.springboot.familyProject.resident.domain.ResidentRegistrationNumber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    Resident findByResidentRegistrationNumber(ResidentRegistrationNumber residentRegistrationNumber);
    Resident findById(Identification id);
    Page<Resident> findAll(Pageable pageable);
}
