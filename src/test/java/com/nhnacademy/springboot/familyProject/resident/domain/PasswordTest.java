package com.nhnacademy.springboot.familyProject.resident.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PasswordTest {

    @DisplayName("비밀번호가 빈 값이면 에러가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void validatePasswordNotEmpty(String password) {
        assertThatThrownBy(() -> new Password(password))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("비밀번호는 빈값이 허용되지 않습니다.");
    }

    @DisplayName("비밀번호가 10글자 미만 20글자 초과이면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = { "123456789", "abcd", "dlgusghWkdWKdajtdlTek12345" })
    void validatePasswordLength(String password) {
        assertThatThrownBy(() -> new Password(password))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("비밀번호의 길이는 10글자 미만 20글자 초과  될 수 없습니다.");
    }

    @DisplayName("비밀번호에 특수문자 ~,!,@,#,$중 하나 이상이 포함되지 않으면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = { "gusgh123456", "%%gusghWkd123", "^^dlgusgh1234" })
    void validatePasswordContainSpecialLetters(String password) {
        assertThatThrownBy(() -> new Password(password))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("비밀번호는 ~,!,@,#,$중 하나가 포함되어야 합니다.");
    }

    @DisplayName("비밀번호에 영문자중 하나 이상이 포함되지 않으면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = { "@#$12345600", "!@!@!@!@!@!@!@", "~~!@#$12345" })
    void validatePasswordContainAlphabet(String password) {
        assertThatThrownBy(() -> new Password(password))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("비밀번호는 영문자 중 최소 하나가 포함되어야 합니다.");
    }

    @DisplayName("비밀번호에 숫자중 하나 이상이 포함되지 않으면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = { "@#$qweertvvv", "!@!@!@!@!@!@!@qw", "gusghek~~!!" })
    void validatePasswordContainNumber(String password) {
        assertThatThrownBy(() -> new Password(password))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("비밀번호는 숫자 중 최소 하나가 포함되어야 합니다.");
    }
}