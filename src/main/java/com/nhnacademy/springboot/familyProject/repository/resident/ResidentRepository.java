package com.nhnacademy.springboot.familyProject.repository.resident;

import com.nhnacademy.springboot.familyProject.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    Resident findByResidentRegistrationNumber(String residentRegistrationNumber);
    Resident findById(String id);
}
