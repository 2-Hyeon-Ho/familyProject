package com.nhnacademy.springboot.familyProject.resident.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Email {

    public static final String EMAIL_FORM_IS_INCORRECT = "이메일 형식에 맞지 않습니다.";
    @Column(name = "email_address")
    private String email;

    public Email(String email) {
        validate(email);
        this.email = email;
    }

    private void validate(String email) {
        validateEmailForm(email);
    }

    private void validateEmailForm(String email) {
        if(!email.contains("@")) {
            throw new IllegalArgumentException(EMAIL_FORM_IS_INCORRECT);
        }
    }
}
