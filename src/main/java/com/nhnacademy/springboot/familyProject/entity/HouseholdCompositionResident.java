package com.nhnacademy.springboot.familyProject.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "household_composition_resident")
public class HouseholdCompositionResident {

    @EmbeddedId
    private Pk pk;

    @Column(name = "report_date")
    private LocalDate reportDate;

    @Column(name = "household_relationship_code")
    private String householdRelationshipCode;

    @Column(name = "household_composition_change_reason_code")
    private String householdCompositionChangeReasonCode;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(referencedColumnName = "household_serial_number", name = "household_serial_number")
    private Household houseHold;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(referencedColumnName = "resident_serial_number", name = "resident_serial_number")
    private Resident resident;

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Embeddable
    public static class Pk implements Serializable {

        @Column(name = "household_serial_number")
        private Integer householdSerialNumber;

        @Column(name = "resident_serial_number")
        private Integer residentSerialNumber;
    }
}
