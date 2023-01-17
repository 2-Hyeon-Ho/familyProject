package com.nhnacademy.springboot.familyProject.resident.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class EmailTest {

    @DisplayName("이메일 형식에 맞지 않으면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = { "abcd#naver.com", "abc123@naver", "abc123@@naver.com" })
    void validatePasswordNotEmpty(String email) {
        assertThatThrownBy(() -> new Email(email))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이메일 형식에 맞지 않습니다.");
    }
}