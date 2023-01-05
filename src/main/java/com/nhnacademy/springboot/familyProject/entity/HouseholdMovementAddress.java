package com.nhnacademy.springboot.familyProject.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "household_movement_address")
public class HouseholdMovementAddress {

    @EmbeddedId
    private Pk pk;

    @Column(name = "house_movement_address")
    private String houseMovementAddress;

    @Column(name = "last_address_yn")
    private String lastAddressYn;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(referencedColumnName = "household_serial_number", name = "household_serial_number")
    private Household houseHold;

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    @Getter
    public static class Pk implements Serializable {

        @Column(name = "house_movement_report_date")
        private LocalDate houseMovementReportDate;

        @Column(name = "household_serial_number")
        private Integer householdSerialNumber;
    }

    public HouseholdMovementAddress updateLastAddressYn(String lastAddressYn) {
        this.lastAddressYn = lastAddressYn;

        return this;
    }

    public HouseholdMovementAddress update(String houseMovementAddress) {
        this.houseMovementAddress = houseMovementAddress;

        return this;
    }
}
