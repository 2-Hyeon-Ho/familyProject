package com.nhnacademy.springboot.familyProject.resident.domain;


import static com.nhnacademy.springboot.familyProject.config.SecurityConfig.passwordEncoder;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResidentTest {
    private Resident resident;

    @BeforeEach
    void setUp() {
        resident = Resident.builder()
            .name(new Name("HyeonHo"))
            .residentRegistrationNumber(new ResidentRegistrationNumber("980628-1632118"))
            .genderCode(GenderCode.남)
            .birthDate(LocalDateTime.of(1998, 6, 28, 12, 00, 00, 00))
            .birthPlaceCode(BirthPlaceCode.병원)
            .registrationBaseAddress("Yeosu-Si")
            .id(new Identification("bam6469"))
            .password(new Password("@abc12345678"))
            .email(new Email("bam6469@naver.com"))
            .build();
    }


    @DisplayName("주민을 업데이트한다.")
    @Test
    void update() {
        resident.update(
            null,
            new Identification("bam201"),
            new Password("!abc12345678"),
            null
        );

        assertAll(
            () -> Assertions.assertThat(resident.getName()).isEqualTo(new Name("HyeonHo")),
            () -> Assertions.assertThat(resident.getId()).isEqualTo(new Identification("bam201")),
            () -> passwordEncoder().matches("!abc12345678", resident.getPassword().getPassword()),
            () -> Assertions.assertThat(resident.getEmail()).isEqualTo(new Email("bam6469@naver.com"))
        );
    }

    @DisplayName("사망주민을 업데이트한다.")
    @Test
    void updateDeath() {
        resident.updateDeath(
            LocalDateTime.of(2023, 1, 22, 12, 00, 00),
            DeathPlaceCode.도로,
            "광주광역시...."
        );

        assertAll(
            () -> Assertions.assertThat(resident.getDeathDate().format(DateTimeFormatter.ISO_DATE_TIME)).isEqualTo("2023-01-22T12:00:00"),
            () -> Assertions.assertThat(resident.getDeathPlaceCode()).isEqualTo(DeathPlaceCode.도로),
            () -> Assertions.assertThat(resident.getDeathPlaceAddress()).isEqualTo("광주광역시....")
        );
    }

}