package com.nhnacademy.springboot.familyProject.resident.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.nhnacademy.springboot.familyProject.exception.DataDuplicateException;
import com.nhnacademy.springboot.familyProject.resident.domain.Resident;
import com.nhnacademy.springboot.familyProject.resident.dto.ResidentCreateRequest;
import com.nhnacademy.springboot.familyProject.resident.dto.ResidentResponse;
import com.nhnacademy.springboot.familyProject.resident.repository.ResidentRepository;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ResidentServiceTest {

    @InjectMocks
    private ResidentService residentService;
    @Mock
    private ResidentRepository residentRepository;
    private ResidentCreateRequest residentCreateRequest;
    private Resident resident;

    @BeforeEach
    void setUp() {
        residentRepository = mock(ResidentRepository.class);
        residentService = new ResidentService(residentRepository);

        residentCreateRequest = new ResidentCreateRequest(
            "이혀노",
            "980628-0000000",
            "남",
            LocalDateTime.of(1998,6,28,12,0,0),
            "병원",
            "전남 여수",
            "bam6469",
            "@gusgh1234567",
            "bam6469@naver.com"
        );

        resident = residentCreateRequest.toResident();
    }

    @Test
    @DisplayName("주민 등록 성공")
    void createResident() {
        //when
        when(residentRepository.save(any(Resident.class))).thenReturn(resident);
        ResidentResponse result = residentService.createResident(residentCreateRequest);

        //then
        assertAll(
            () -> Assertions.assertThat(result.getName()).isEqualTo(resident.getName().getName()),
            () -> Assertions.assertThat(result.getResidentRegistrationNumber()).isEqualTo(resident.getResidentRegistrationNumber().getResidentRegistrationNumber()),
            () -> Assertions.assertThat(result.getGenderCode()).isEqualTo(resident.getGenderCode().name()),
            () -> Assertions.assertThat(result.getBirthDate()).isEqualTo(resident.getBirthDate()),
            () -> Assertions.assertThat(result.getBirthPlaceCode()).isEqualTo(resident.getBirthPlaceCode().name()),
            () -> Assertions.assertThat(result.getRegistrationBaseAddress()).isEqualTo(resident.getRegistrationBaseAddress()),
            () -> Assertions.assertThat(result.getId()).isEqualTo(resident.getId().getId()),
            () -> Assertions.assertThat(result.getEmail()).isEqualTo(resident.getEmail().getEmail())
        );
    }

    @Test
    @DisplayName("주민등록번호 중복으로 주민등록실패")
    void duplicatedRegistrationNumber() {
        when(residentRepository.save(any(Resident.class))).thenReturn(resident);
        residentService.createResident(residentCreateRequest);

        ResidentCreateRequest newResidentCreateRequest = new ResidentCreateRequest(
            "삼혀노",
            "980628-0000000",
            "여",
            LocalDateTime.of(1998,6,28,12,0,0),
            "병원",
            "광주광역시",
            "abc12345",
            "@gush12345678",
            "abc12345@naver.com"
        );
        Resident newResident = newResidentCreateRequest.toResident();

        when(residentRepository.save(any(Resident.class))).thenReturn(newResident);
        Assertions.assertThatThrownBy(() -> residentService.createResident(newResidentCreateRequest))
            .isInstanceOf(DataDuplicateException.class);
    }

    @Test
    @DisplayName("주민 조회 성공")
    void readAllResident() {

    }

    @Test
    void updateResident() {
    }

    @Test
    void updateDeathResident() {
    }

    @Test
    void deleteResident() {
    }
}