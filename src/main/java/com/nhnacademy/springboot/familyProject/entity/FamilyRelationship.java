package com.nhnacademy.springboot.familyProject.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "family_relationship")
public class FamilyRelationship {

    @EmbeddedId
    private Pk pk;

    @Column(name = "family_relationship_code")
    private String familyRelationshipCode;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne
    @JoinColumn(referencedColumnName = "resident_serial_number", name = "base_resident_serial_number")
    private Resident resident;


    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {

        @Column(name = "family_resident_serial_number")
        private Integer familyResidentRegistrationNumber;

        @Column(name = "base_resident_serial_number")
        private Integer baseResidentSerialNumber;
    }
}
