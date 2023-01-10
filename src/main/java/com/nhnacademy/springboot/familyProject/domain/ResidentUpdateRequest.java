package com.nhnacademy.springboot.familyProject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class ResidentUpdateRequest {
    @Nullable
    private String name;
    @Nullable
    private String id;
    @Nullable
    private String password;
    @Nullable
    private String email;
}
