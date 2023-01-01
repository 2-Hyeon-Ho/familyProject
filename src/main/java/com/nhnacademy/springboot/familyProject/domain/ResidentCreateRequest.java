package com.nhnacademy.springboot.familyProject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
public class ResidentCreateRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(min = 13)
    private String residentRegistrationNumber;

    @NotBlank
    private String genderCode;

    @NotNull
    @PastOrPresent
    private LocalDateTime birthDate;

    @NotBlank
    @Size(max = 20)
    private String birthPlaceCode;

    @NotBlank
    @Size(max = 500)
    private String registrationBaseAddress;

    @Nullable
    @FutureOrPresent
    private LocalDateTime deathDate;

    @Nullable
    @Size(max = 20)
    private String deathPlaceCode;

    @Nullable
    @Size(max = 500)
    private String deathPlaceAddress;

    @NotBlank
    @Size(max = 20)
    private String id;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
}
