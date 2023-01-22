package com.nhnacademy.springboot.familyProject.familyRelationship.domain;


import com.nhnacademy.springboot.familyProject.familyRelationship.domain.FamilyRelationship.Pk;
import com.nhnacademy.springboot.familyProject.resident.domain.BirthPlaceCode;
import com.nhnacademy.springboot.familyProject.resident.domain.Email;
import com.nhnacademy.springboot.familyProject.resident.domain.GenderCode;
import com.nhnacademy.springboot.familyProject.resident.domain.Identification;
import com.nhnacademy.springboot.familyProject.resident.domain.Name;
import com.nhnacademy.springboot.familyProject.resident.domain.Password;
import com.nhnacademy.springboot.familyProject.resident.domain.Resident;
import com.nhnacademy.springboot.familyProject.resident.domain.ResidentRegistrationNumber;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class FamilyRelationshipTest {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @DisplayName("가족관계를 업데이트한다.")
    @Test
    void update() {
        Resident baseResident = Resident.builder()
            .name(new Name("HyeonHo"))
            .residentRegistrationNumber(new ResidentRegistrationNumber("980628-1632118"))
            .genderCode(GenderCode.남)
            .birthDate(LocalDateTime.of(1998, 6, 28, 12, 00, 00, 00))
            .birthPlaceCode(BirthPlaceCode.병원)
            .registrationBaseAddress("Yeosu-Si")
            .id(new Identification("bam6469"))
            .password(new Password("@abc12345678", passwordEncoder))
            .email(new Email("bam6469@naver.com"))
            .build();
        Resident targetResident = Resident.builder()
            .name(new Name("HoHyeon"))
            .residentRegistrationNumber(new ResidentRegistrationNumber("080628-3131313"))
            .genderCode(GenderCode.남)
            .birthDate(LocalDateTime.of(2008, 6, 28, 12, 00, 00, 00))
            .birthPlaceCode(BirthPlaceCode.병원)
            .registrationBaseAddress("Gwangju")
            .id(new Identification("abc6469"))
            .password(new Password("@abc12345678", passwordEncoder))
            .email(new Email("abc6469@naver.com"))
            .build();

        FamilyRelationship familyRelationship = FamilyRelationship.builder()
            .pk(new Pk(targetResident.getResidentId(), baseResident.getResidentId()))
            .familyRelationshipCode(FamilyRelationshipCode.부)
            .resident(baseResident)
            .build();

        familyRelationship.update(
            FamilyRelationshipCode.자녀
        );

        Assertions.assertThat(familyRelationship.getFamilyRelationshipCode()).isEqualTo(FamilyRelationshipCode.자녀);
    }
}