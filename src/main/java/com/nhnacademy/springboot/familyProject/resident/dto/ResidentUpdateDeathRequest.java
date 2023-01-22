package com.nhnacademy.springboot.familyProject.resident.dto;

import com.nhnacademy.springboot.familyProject.resident.domain.DeathPlaceCode;
import com.nhnacademy.springboot.familyProject.resident.domain.Email;
import com.nhnacademy.springboot.familyProject.resident.domain.Identification;
import com.nhnacademy.springboot.familyProject.resident.domain.Name;
import com.nhnacademy.springboot.familyProject.resident.domain.Password;
import com.nhnacademy.springboot.familyProject.resident.domain.Resident;
import java.time.LocalDateTime;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResidentUpdateDeathRequest {

    public static final String DEATH_PLACE_ADDRESS_LENGTH_IS_OVER_FIVE_HUNDRED = "사망장소 주소는 500글자를 초과할 수 없습니다.";

    private LocalDateTime deathDate;

    private String deathPlaceCode;

    @Size(max = 500, message = DEATH_PLACE_ADDRESS_LENGTH_IS_OVER_FIVE_HUNDRED)
    private String deathPlaceAddress;
}
