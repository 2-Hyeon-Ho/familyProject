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
public class Identification {

    public static final String ID_IS_NOT_EMPTY = "ID는 빈값이 허용되지 않습니다.";
    public static final String ID_LENGTH_IS_INCORRECT = "ID의 길이는 4글자 미만 12글자 초과가 될 수 없습니다.";
    @Column(name = "id", nullable = false)
    private String id;

    public Identification(String id) {
        validate(id);
        this.id = id;
    }

    private void validate(String id) {
        validateIdNotEmpty(id);
        validateIdLength(id);
    }

    private void validateIdNotEmpty(String id) {
        if(Objects.isNull(id) || id.isEmpty()) {
            throw new IllegalArgumentException(ID_IS_NOT_EMPTY);
        }
    }

    private void validateIdLength(String id) {
        if(id.length() < 4 || id.length() > 12) {
            throw new IllegalArgumentException(ID_LENGTH_IS_INCORRECT);
        }
    }
}
