package com.nhnacademy.springboot.familyProject.resident.domain;

import com.nhnacademy.springboot.familyProject.common.constant.ErrorCode;
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
            throw new IllegalArgumentException(ErrorCode.RESIDENT_REGISTRATION_NUMBER_IS_NOT_EMPTY.getErrorMessage());
        }
    }

    private void validateResidentRegistrationNumberForm(String residentRegistrationNumber) {
        if(isCorrectLength(residentRegistrationNumber) || isCorrectForm(residentRegistrationNumber)) {
            throw new IllegalArgumentException(ErrorCode.RESIDENT_REGISTRATION_NUMBER_IS_INCORRECT_FORM.getErrorMessage());
        }
    }

    private boolean isCorrectLength(String residentRegistrationNumber) {
        return residentRegistrationNumber.length() < 13 || residentRegistrationNumber.length() > 14;
    }

    private boolean isCorrectForm(String residentRegistrationNumber) {
        return !pattern.matcher(residentRegistrationNumber).matches();
    }
}
