package com.nhnacademy.springboot.familyProject.resident.domain;

import lombok.Getter;

public enum DeathPlaceCode {

    HOUSE("주택"), MEDICAL_INSTITUTIONS("의료기관"), SOCIAL_WELFARE_ORGANS("사회복지시설"), INDUSTRIAL_FIELDS("산업장"),
    PUBLIC_FACILITY("공공시설"), ROAD("도로"), COMMERCIAL_FACILITY("상업시설"), FARM("농장"),
    TRANSPORTED_TO_HOSPITAL("병원 이송 중 사망"), THE_OTHERS("기타");

    @Getter
    private final String value;

    DeathPlaceCode(String value) {
        this.value = value;
    }
}
