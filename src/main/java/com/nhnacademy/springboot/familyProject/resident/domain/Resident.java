package com.nhnacademy.springboot.familyProject.resident.domain;

import com.nhnacademy.springboot.familyProject.familyRelationship.domain.FamilyRelationship;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resident")
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resident_serial_number")
    private Integer residentId;

    @Embedded
    private Name name;

    @Embedded
    private ResidentRegistrationNumber residentRegistrationNumber;

    @Column(name = "gender_code", nullable = false)
    private GenderCode genderCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @Column(name = "birth_place_code", nullable = false)
    private BirthPlaceCode birthPlaceCode;

    @Column(name = "registration_base_address", nullable = false)
    private String registrationBaseAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "death_place_code")
    private DeathPlaceCode deathPlaceCode;

    @Column(name = "death_place_address")
    private String deathPlaceAddress;

    @OneToMany(mappedBy = "resident")
    private List<FamilyRelationship> familyRelationship;

    @Embedded
    private Identification id;

    @Embedded
    private Password password;

    @Embedded
    private Email email;
}
