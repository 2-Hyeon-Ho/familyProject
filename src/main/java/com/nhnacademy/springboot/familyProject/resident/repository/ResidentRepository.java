package com.nhnacademy.springboot.familyProject.resident.repository;

import com.nhnacademy.springboot.familyProject.resident.domain.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    Resident findByResidentRegistrationNumber(String residentRegistrationNumber);
    Resident findById(String id);
}
