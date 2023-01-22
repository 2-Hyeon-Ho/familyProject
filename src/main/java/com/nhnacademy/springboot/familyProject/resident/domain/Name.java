package com.nhnacademy.springboot.familyProject.resident.domain;


import com.nhnacademy.springboot.familyProject.common.constant.ErrorCode;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Name {

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
            throw new IllegalArgumentException(ErrorCode.NAME_IS_NOT_EMPTY.getErrorMessage());
        }
    }

}
