package com.nhnacademy.springboot.familyProject.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "household")
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "household_serial_number")
    private Integer householdSerialNumber;

    @ManyToOne
    @JoinColumn(name = "household_resident_serial_number")
    private Resident resident;

    @Column(name = "household_composition_date")
    private LocalDate householdCompositionDate;

    @Column(name = "household_composition_reason_code")
    private String householdCompositionReasonCode;

    @Column(name = "current_house_movement_address")
    private String currentHouseMovementAddress;

    @OneToMany(mappedBy = "houseHold")
    private List<HouseholdCompositionResident> householdCompositionResidents;

    public Household update(List<HouseholdCompositionResident> householdCompositionResidents) {
        this.householdCompositionResidents = householdCompositionResidents;

        return this;
    }
}
