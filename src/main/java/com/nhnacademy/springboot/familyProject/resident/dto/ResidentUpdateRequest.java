package com.nhnacademy.springboot.familyProject.resident.dto;

import com.nhnacademy.springboot.familyProject.resident.domain.Email;
import com.nhnacademy.springboot.familyProject.resident.domain.Identification;
import com.nhnacademy.springboot.familyProject.resident.domain.Name;
import com.nhnacademy.springboot.familyProject.resident.domain.Password;
import com.nhnacademy.springboot.familyProject.resident.domain.Resident;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResidentUpdateRequest {

    private static final String NAME_LENGTH_IS_OVER_ELEVEN = "이름은 11글자를 초과할 수 없습니다.";

    @Nullable
    @Size(max = 11, message = NAME_LENGTH_IS_OVER_ELEVEN)
    private String name;
    @Nullable
    private String id;
    @Nullable
    private String password;
    @Nullable
    private String email;
}
