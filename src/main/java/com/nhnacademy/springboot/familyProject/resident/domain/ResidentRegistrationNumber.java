package com.nhnacademy.springboot.familyProject.resident.domain;

import java.util.Objects;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class ResidentRegistrationNumber {

    public static final String RESIDENT_REGISTRATION_NUMBER_IS_NOT_EMPTY = "주민등록번호는 빈값이 허용되지 않습니다.";
    public static final String RESIDENT_REGISTRATION_NUMBER_IS_INCORRECT_FORM = "주민등록번호의 형식이 알맞지 않습니다.";

    private static final Pattern pattern = Pattern.compile("^[0-9\\-]*$");

    @Column(name = "resident_registration_number", nullable = false)
    private String residentRegistrationNumber;

    public ResidentRegistrationNumber(String residentRegistrationNumber) {
        validate(residentRegistrationNumber);
        this.residentRegistrationNumber = residentRegistrationNumber;
    }

    private void validate(String residentRegistrationNumber) {
        validateResidentRegistrationNumberNotEmpty(residentRegistrationNumber);
        validateResidentRegistrationNumberForm(residentRegistrationNumber);
    }

    private void validateResidentRegistrationNumberNotEmpty(String residentRegistrationNumber) {
        if(Objects.isNull(residentRegistrationNumber) || residentRegistrationNumber.isEmpty()) {
            throw new IllegalArgumentException(RESIDENT_REGISTRATION_NUMBER_IS_NOT_EMPTY);
        }
    }

    private void validateResidentRegistrationNumberForm(String residentRegistrationNumber) {
        if(isCorrectLength(residentRegistrationNumber) || isCorrectForm(residentRegistrationNumber)) {
            throw new IllegalArgumentException(RESIDENT_REGISTRATION_NUMBER_IS_INCORRECT_FORM);
        }
    }

    private boolean isCorrectLength(String residentRegistrationNumber) {
        return residentRegistrationNumber.length() < 13 || residentRegistrationNumber.length() > 14;
    }

    private boolean isCorrectForm(String residentRegistrationNumber) {
        return !pattern.matcher(residentRegistrationNumber).matches();
    }
}
