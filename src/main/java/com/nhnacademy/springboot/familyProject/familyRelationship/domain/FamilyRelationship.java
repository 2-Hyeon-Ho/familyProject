package com.nhnacademy.springboot.familyProject.familyRelationship.domain;

import com.nhnacademy.springboot.familyProject.resident.domain.Resident;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "family_relationship_code")
    private FamilyRelationshipCode familyRelationshipCode;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne
    @JoinColumn(referencedColumnName = "resident_serial_number", name = "base_resident_serial_number")
    private Resident resident;


    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    @Getter
    public static class Pk implements Serializable {

        @Column(name = "family_resident_serial_number")
        private Integer familyResidentRegistrationNumber;

        @Column(name = "base_resident_serial_number")
        private Integer baseResidentSerialNumber;
    }

    public void update(FamilyRelationshipCode familyRelationshipCode) {
        this.familyRelationshipCode = familyRelationshipCode;
    }
}
