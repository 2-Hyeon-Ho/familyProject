package com.nhnacademy.springboot.familyProject.resident.domain;

import com.nhnacademy.springboot.familyProject.familyRelationship.domain.FamilyRelationship;
import java.util.Objects;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "gender_code", nullable = false)
    private GenderCode genderCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "birth_place_code", nullable = false)
    private BirthPlaceCode birthPlaceCode;

    @Column(name = "registration_base_address", nullable = false)
    private String registrationBaseAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Enumerated(EnumType.STRING)
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

    public void update(Name name, Identification id, Password password, Email email) {
        if(Objects.nonNull(name)) {
            this.name = name;
        }
        if(Objects.nonNull(id)) {
            this.id = id;
        }
        if(Objects.nonNull(password)) {
            this.password = password;
        }
        if(Objects.nonNull(email)) {
            this.email = email;
        }
    }

    public void updateDeath(LocalDateTime deathDate, DeathPlaceCode deathPlaceCode, String deathPlaceAddress) {
        this.deathDate = deathDate;
        this.deathPlaceCode = deathPlaceCode;
        this.deathPlaceAddress = deathPlaceAddress;
    }
}
