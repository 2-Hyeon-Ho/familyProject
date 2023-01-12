package com.nhnacademy.springboot.familyProject.household.controller;

import com.nhnacademy.springboot.familyProject.household.dto.HouseholdResponse;
import com.nhnacademy.springboot.familyProject.household.dto.HouseholdRequest;
import com.nhnacademy.springboot.familyProject.exception.AcceptHeaderNotValidException;
import com.nhnacademy.springboot.familyProject.exception.ValidationFailedException;
import com.nhnacademy.springboot.familyProject.household.service.HouseholdService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/household")
public class HouseholdRestController {
    private final HouseholdService householdService;

    public HouseholdRestController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HouseholdResponse createHousehold(@Valid @RequestBody HouseholdRequest householdRequest,
                                        BindingResult bindingResult,
                                        HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String accept = request.getHeader("Accept");
        if(!accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        return householdService.createHousehold(householdRequest);
    }

    @DeleteMapping("/{householdSerialNumber}")
    public void deleteHousehold(@PathVariable("householdSerialNumber") Integer householdSerialNumber) {
        householdService.deleteHousehold(householdSerialNumber);
    }
}
