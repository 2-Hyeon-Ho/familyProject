package com.nhnacademy.springboot.familyProject.resident.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Password {

    public static final String PASSWORD_IS_NOT_EMPTY = "비밀번호는 빈값이 허용되지 않습니다.";
    public static final String PASSWORD_LENGTH_IS_INCORRECT = "비밀번호의 길이는 10글자 미만 20글자 초과  될 수 없습니다.";
    public static final String PASSWORD_IS_NOT_CONTAIN_SPECIAL_LETTERS = "비밀번호는 ~,!,@,#,$중 하나가 포함되어야 합니다.";
    public static final String PASSWORD_IS_NOT_CONTAIN_ALPHABET = "비밀번호는 영문자 중 최소 하나가 포함되어야 합니다.";
    public static final String PASSWORD_IS_NOT_CONTAIN_NUMBER = "비밀번호는 숫자 중 최소 하나가 포함되어야 합니다.";
    @Column(name = "password", nullable = false)
    private String password;

    public Password(String password) {
        validate(password);
        this.password = password;
    }

    private void validate(String password) {
        validatePasswordNotEmpty(password);
        validatePasswordLength(password);
        checkSpecialLetters(password);
        checkAlphabets(password);
        checkNumbers(password);
    }

    private void validatePasswordNotEmpty(String password) {
        if(Objects.isNull(password) || password.isEmpty()) {
            throw new IllegalArgumentException(PASSWORD_IS_NOT_EMPTY);
        }
    }

    private void validatePasswordLength(String password) {
        if(password.length() < 10 || password.length() > 20) {
            throw new IllegalArgumentException(PASSWORD_LENGTH_IS_INCORRECT);
        }
    }

    private void checkSpecialLetters(String password) {
        if(!(password.contains("~") || password.contains("!") || password.contains("@")
            || password.contains("#") || password.contains("$"))) {
            throw new IllegalArgumentException(PASSWORD_IS_NOT_CONTAIN_SPECIAL_LETTERS);
        }
    }

    private void checkAlphabets(String password) {
        if(password.chars().noneMatch(Character::isLetter)) {
            throw new IllegalArgumentException(PASSWORD_IS_NOT_CONTAIN_ALPHABET);
        }
    }

    private void checkNumbers(String password) {
        if(!password.matches(".*[0-9].*")) {
            throw new IllegalArgumentException(PASSWORD_IS_NOT_CONTAIN_NUMBER);
        }
    }
}
