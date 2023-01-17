package com.nhnacademy.springboot.familyProject.resident.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class NameTest {

    @DisplayName("이름이 빈 값이면 에러가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void validateNameNotEmpty(String name) {
        assertThatThrownBy(() -> new Name(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름은 빈값이 허용되지 않습니다");
    }

}