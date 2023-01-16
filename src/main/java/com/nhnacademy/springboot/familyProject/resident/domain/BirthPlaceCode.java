package com.nhnacademy.springboot.familyProject.resident.domain;


import lombok.Getter;

public enum BirthPlaceCode {

    OWNS_HOME("자택"), HOSPITAL("병원"), THE_OTHERS("기타");

    @Getter
    private final String value;

    BirthPlaceCode(String value) {
        this.value = value;
    }
}
