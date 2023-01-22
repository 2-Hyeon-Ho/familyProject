package com.nhnacademy.springboot.familyProject.resident.controller;

import com.nhnacademy.springboot.familyProject.common.constant.ErrorCode;
import com.nhnacademy.springboot.familyProject.exception.CustomException;
import com.nhnacademy.springboot.familyProject.resident.dto.ResidentCreateRequest;
import com.nhnacademy.springboot.familyProject.resident.dto.ResidentResponse;
import com.nhnacademy.springboot.familyProject.resident.dto.ResidentUpdateDeathRequest;
import com.nhnacademy.springboot.familyProject.resident.dto.ResidentUpdateRequest;
import com.nhnacademy.springboot.familyProject.exception.AcceptHeaderNotValidException;
import com.nhnacademy.springboot.familyProject.exception.ResidentNotFoundException;
import com.nhnacademy.springboot.familyProject.exception.ValidationFailedException;
import com.nhnacademy.springboot.familyProject.resident.service.ResidentService;
import java.net.URI;
import java.util.List;
import javax.validation.constraints.Max;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/residents")
public class ResidentRestController {
    private final ResidentService residentService;

    public ResidentRestController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping
    public ResponseEntity<ResidentResponse> createResident(@Valid @RequestBody final ResidentCreateRequest resident,
                                      BindingResult bindingResult,
                                      HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String accept = request.getHeader("Accept");
        if (!accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }
        ResidentResponse residentResponse = residentService.createResident(resident);
        URI uri = URI.create("/resident/" + residentResponse.getResidentId());
        return ResponseEntity.created(uri).body(residentResponse);
    }

    @GetMapping
    public ResponseEntity<List<ResidentResponse>> readAllResident(
        @RequestParam(required = false, defaultValue = "0") @Max(value = 999) int page,
        @RequestParam(required = false, defaultValue = "10") @Max(value = 100) int size,
        HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if(!accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }
        List<ResidentResponse> residentResponses = residentService.readAllResident(
            PageRequest.of(page, size));
        return ResponseEntity.ok().body(residentResponses);
    }

    @PutMapping("/{residentId}")
    public ResponseEntity<ResidentResponse> updateResident(@Valid @RequestBody ResidentUpdateRequest residentUpdateRequest,
                                      @PathVariable("residentId") Integer residentId,
                                      BindingResult bindingResult,
                                      HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String accept = request.getHeader("Accept");
        if (!accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }
        if (Objects.isNull(residentId)) {
            throw new CustomException(ErrorCode.PARAMETER_NOT_EXIST);
        }

        ResidentResponse residentResponse = residentService.updateResident(residentId,
            residentUpdateRequest);
        return ResponseEntity.ok().body(residentResponse);
    }

    @PutMapping("/death/{residentId}")
    public ResponseEntity<ResidentResponse> updateDeathResident(@Valid @RequestBody ResidentUpdateDeathRequest residentUpdateDeathRequest,
        @PathVariable("residentId") Integer residentId,
        BindingResult bindingResult,
        HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String accept = request.getHeader("Accept");
        if (!accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }
        if (Objects.isNull(residentId)) {
            throw new CustomException(ErrorCode.PARAMETER_NOT_EXIST);
        }

        ResidentResponse residentResponse = residentService.updateDeathResident(residentId,
            residentUpdateDeathRequest);
        return ResponseEntity.ok().body(residentResponse);
    }

    @DeleteMapping("/{residentId}")
    public ResponseEntity<ResidentResponse> deleteResident(@PathVariable("residentId") Integer residentId, HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if (!accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }
        if (Objects.isNull(residentId)) {
            throw new CustomException(ErrorCode.PARAMETER_NOT_EXIST);
        }

        ResidentResponse residentResponse = residentService.deleteResident(residentId);
        return ResponseEntity.ok().body(residentResponse);
    }
}
