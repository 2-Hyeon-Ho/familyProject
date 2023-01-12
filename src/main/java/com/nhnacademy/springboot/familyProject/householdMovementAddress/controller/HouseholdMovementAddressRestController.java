package com.nhnacademy.springboot.familyProject.householdMovementAddress.controller;

import com.nhnacademy.springboot.familyProject.householdMovementAddress.dto.HouseholdMovementAddressDto;
import com.nhnacademy.springboot.familyProject.householdMovementAddress.dto.HouseholdMovementAddressRequest;
import com.nhnacademy.springboot.familyProject.exception.AcceptHeaderNotValidException;
import com.nhnacademy.springboot.familyProject.exception.ValidationFailedException;
import com.nhnacademy.springboot.familyProject.householdMovementAddress.service.HouseholdMovementAddressService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/household/{householdSerialNumber}/movement")
public class HouseholdMovementAddressRestController {
    private final HouseholdMovementAddressService householdMovementAddressService;

    public HouseholdMovementAddressRestController(HouseholdMovementAddressService householdMovementAddressService) {
        this.householdMovementAddressService = householdMovementAddressService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HouseholdMovementAddressDto createHouseholdMovementAddress(@PathVariable("householdSerialNumber") Integer householdSerialNumber,
                                                                      @Valid @RequestBody HouseholdMovementAddressRequest householdMovementAddressRequest,
                                                                      BindingResult bindingResult,
                                                                      HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String accept = request.getHeader("Accept");
        if(!accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        return householdMovementAddressService.createHouseholdMovementAddress(householdSerialNumber, householdMovementAddressRequest);
    }

    @PutMapping("/{reportDate}")
    public HouseholdMovementAddressDto updateHouseholdMovementAddress(@PathVariable("householdSerialNumber") Integer householdSerialNumber,
                                                                      @PathVariable("reportDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reportDate,
                                                                      @Valid @RequestBody HouseholdMovementAddressRequest householdMovementAddressRequest,
                                                                      BindingResult bindingResult,
                                                                      HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String accept = request.getHeader("Accept");
        if(!accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        return householdMovementAddressService.updateHouseholdMovementAddress(householdSerialNumber, reportDate, householdMovementAddressRequest);
    }

    @DeleteMapping("/{reportDate}")
    public void deleteHouseholdMovementAddress(@PathVariable("householdSerialNumber") Integer householdSerialNumber,
                                               @PathVariable("reportDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reportDate) {
        householdMovementAddressService.deleteHouseholdMovementAddress(householdSerialNumber, reportDate);
    }
}
