package com.nhnacademy.springboot.familyProject.resident.dto;

import com.nhnacademy.springboot.familyProject.resident.domain.BirthPlaceCode;
import com.nhnacademy.springboot.familyProject.resident.domain.DeathPlaceCode;
import com.nhnacademy.springboot.familyProject.resident.domain.GenderCode;
import com.nhnacademy.springboot.familyProject.resident.domain.Identification;
import com.nhnacademy.springboot.familyProject.resident.domain.Name;
import com.nhnacademy.springboot.familyProject.resident.domain.Password;
import com.nhnacademy.springboot.familyProject.resident.domain.Resident;
import com.nhnacademy.springboot.familyProject.resident.domain.ResidentRegistrationNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResidentCreateRequest {

    private static final String NAME_LENGTH_IS_OVER_ELEVEN = "이름은 11글자를 초과할 수 없습니다.";
    public static final String BIRTH_DATE_IS_NOT_EMPTY = "출생시간은 빈값이 허용되지 않습니다.";
    public static final String BIRTH_DATE_PERMIT_PAST = "출생시간이 현재보다 미래일 수 없습니다.";
    public static final String BASE_ADDRESS_IS_NOT_EMPTY = "등록기준지 주소는 빈값을 허용하지 않습니다.";
    public static final String BASE_ADDRESS_LENGTH_IS_OVER_FIVE_HUNDRED = "등록기준지 주소는 500글자를 초과할 수 없습니다.";


    @Size(max = 11, message = NAME_LENGTH_IS_OVER_ELEVEN)
    private String name;

    private String residentRegistrationNumber;

    private String genderCode;

    @NotNull(message = BIRTH_DATE_IS_NOT_EMPTY)
    @PastOrPresent(message = BIRTH_DATE_PERMIT_PAST)
    private LocalDateTime birthDate;

    private String birthPlaceCode;

    @NotBlank(message = BASE_ADDRESS_IS_NOT_EMPTY)
    @Size(max = 500, message = BASE_ADDRESS_LENGTH_IS_OVER_FIVE_HUNDRED)
    private String registrationBaseAddress;

    private String id;

    private String password;

    private String email;

    public Resident toResident() {
        return Resident.builder()
            .name(new Name(name))
            .residentRegistrationNumber(new ResidentRegistrationNumber(residentRegistrationNumber))
            .genderCode(GenderCode.valueOf(genderCode))
            .birthDate(birthDate)
            .birthPlaceCode(BirthPlaceCode.valueOf(birthPlaceCode))
            .registrationBaseAddress(registrationBaseAddress)
            .deathDate(null)
            .deathPlaceCode(DeathPlaceCode.valueOf("해당없음"))
            .deathPlaceAddress(null)
            .id(new Identification(id))
            .password(new Password(password))
            .email(new com.nhnacademy.springboot.familyProject.resident.domain.Email(email))
            .build();
    }
}
