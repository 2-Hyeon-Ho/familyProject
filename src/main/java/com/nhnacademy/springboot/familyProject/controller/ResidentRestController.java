package com.nhnacademy.springboot.familyProject.controller;

import com.nhnacademy.springboot.familyProject.domain.ResidentCreateRequest;
import com.nhnacademy.springboot.familyProject.domain.ResidentDto;
import com.nhnacademy.springboot.familyProject.domain.ResidentUpdateRequest;
import com.nhnacademy.springboot.familyProject.exception.AcceptHeaderNotValidException;
import com.nhnacademy.springboot.familyProject.exception.ResidentNotFoundException;
import com.nhnacademy.springboot.familyProject.exception.ValidationFailedException;
import com.nhnacademy.springboot.familyProject.service.ResidentService;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public ResidentDto createResident(@Valid @RequestBody ResidentCreateRequest resident,
                                      BindingResult bindingResult,
                                      HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String accept = request.getHeader("Accept");
        if (!accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }
        return residentService.createResident(resident);
    }

    @PutMapping("/{residentId}")
    public ResidentDto updateResident(@Valid @RequestBody ResidentUpdateRequest resident,
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
            throw new ResidentNotFoundException();
        }

        return residentService.updateResident(resident, residentId);
    }
}
