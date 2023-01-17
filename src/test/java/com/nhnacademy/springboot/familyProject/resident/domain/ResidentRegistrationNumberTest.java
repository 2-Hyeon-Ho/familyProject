package com.nhnacademy.springboot.familyProject.resident.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ResidentRegistrationNumberTest {

    @DisplayName("주민등록번호가 빈 값이면 에러가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void validateResidentRegistrationNumberNotEmpty(String residentRegistrationNumber) {
        assertThatThrownBy(() -> new ResidentRegistrationNumber(residentRegistrationNumber))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("주민등록번호는 빈값이 허용되지 않습니다.");
    }

    @DisplayName("주민등록번호가 올바르지 않은 형식이면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = { "12345-1212", "A12345-1234567", "1234567890@212" })
    void validateResidentRegistrationNumberForm(String residentRegistrationNumber) {
        assertThatThrownBy(() -> new ResidentRegistrationNumber(residentRegistrationNumber))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("주민등록번호의 형식이 알맞지 않습니다.");
    }
}