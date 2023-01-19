package com.nhnacademy.springboot.familyProject.common.constant;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    BIRTH_DEATH_REPORT_RESIDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 출생사망신고주민을 찾을 수 없습니다."),
    FAMILY_RELATIONSHIP_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 가족관계를 찾을 수 없습니다."),
    HOUSEHOLD_MOVEMENT_ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 세대전입주소를 찾을 수 없습니다."),
    HOUSEHOLD_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 세대를 찾을 수 없습니다."),
    RESIDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 주민을 찾을 수 없습니다."),
    PARAMETER_NOT_EXIST(HttpStatus.BAD_REQUEST, "변수가 존재하지 않습니다. 변수를 입력해주세요."),
    NAME_IS_NOT_EMPTY(HttpStatus.BAD_REQUEST, "이름은 빈값이 허용되지 않습니다"),
    RESIDENT_REGISTRATION_NUMBER_IS_NOT_EMPTY(HttpStatus.BAD_REQUEST, "주민등록번호는 빈값이 허용되지 않습니다."),
    RESIDENT_REGISTRATION_NUMBER_IS_INCORRECT_FORM(HttpStatus.BAD_REQUEST, "주민등록번호의 형식이 알맞지 않습니다."),
    GENDER_CODE_IS_NOT_EMPTY(HttpStatus.BAD_REQUEST, "성별은 빈값이 허용되지 않습니다."),
    GENDER_CODE_IS_INCORRECT_FORM(HttpStatus.BAD_REQUEST, "성별의 형식은 'M' 또는 'F'입니다."),
    BIRTH_PLACE_CODE_IS_NOT_EMPTY(HttpStatus.BAD_REQUEST, "출생코드는 빈값을 허용하지 않습니다."),
    BIRTH_PLACE_CODE_IS_INCORRECT_FORM(HttpStatus.BAD_REQUEST, "출생코드의 형식은 '자택' 또는 '병원', '기타'입니다.");

    private final HttpStatus status;
    private final String errorMessage;
}
