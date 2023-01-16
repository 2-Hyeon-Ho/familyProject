package com.nhnacademy.springboot.familyProject.resident.domain;

import lombok.Getter;

public enum GenderCode {
    MALE("M"), FEMALE("F");

    @Getter
    private String value;

    GenderCode(String value) {
        this.value = value;
    }
}
