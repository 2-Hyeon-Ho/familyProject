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
public class Name {

    public static final String NAME_IS_NOT_EMPTY = "이름은 빈값이 허용되지 않습니다";
    @Column(name = "name", nullable = false)
    private String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateNameNotEmpty(name);
    }

    private void validateNameNotEmpty(String name) {
        if(Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException(NAME_IS_NOT_EMPTY);
        }
    }

}
