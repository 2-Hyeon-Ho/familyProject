package com.nhnacademy.springboot.familyProject.resident.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class IdentificationTest {

    @DisplayName("ID가 빈 값이면 에러가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void validateIdNotEmpty(String id) {
        assertThatThrownBy(() -> new Identification(id))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("ID는 빈값이 허용되지 않습니다.");
    }

    @DisplayName("ID가 4글자 미만 12글자 초과이면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = { "abc", "123", "abcde12345678" })
    void validateIdLength(String id) {
        assertThatThrownBy(() -> new Identification(id))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("ID의 길이는 4글자 미만 12글자 초과가 될 수 없습니다.");
    }

}