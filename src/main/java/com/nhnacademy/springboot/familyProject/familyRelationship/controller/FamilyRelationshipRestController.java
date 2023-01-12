package com.nhnacademy.springboot.familyProject.familyRelationship.controller;

import com.nhnacademy.springboot.familyProject.familyRelationship.dto.FamilyRelationshipCreateRequest;
import com.nhnacademy.springboot.familyProject.familyRelationship.dto.FamilyRelationshipResponse;
import com.nhnacademy.springboot.familyProject.familyRelationship.dto.FamilyRelationshipUpdateRequest;
import com.nhnacademy.springboot.familyProject.exception.AcceptHeaderNotValidException;
import com.nhnacademy.springboot.familyProject.exception.ValidationFailedException;
import com.nhnacademy.springboot.familyProject.familyRelationship.service.FamilyRelationshipService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/residents/{serialNumber}/relationship")
public class FamilyRelationshipRestController {
    private final FamilyRelationshipService familyRelationshipService;

    public FamilyRelationshipRestController(FamilyRelationshipService familyRelationshipService) {
        this.familyRelationshipService = familyRelationshipService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FamilyRelationshipResponse createFamilyRelationship(@PathVariable("serialNumber") Integer serialNumber,
                                                          @Valid @RequestBody FamilyRelationshipCreateRequest familyRelationship,
                                                          BindingResult bindingResult,
                                                          HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String accept = request.getHeader("Accept");
        if(!accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }
        return familyRelationshipService.createFamilyRelationship(serialNumber, familyRelationship);
    }

    @PutMapping("/{familySerialNumber}")
    public FamilyRelationshipResponse updateFamilyRelationship(@PathVariable("serialNumber") Integer serialNumber,
                                                          @PathVariable("familySerialNumber") Integer familySerialNumber,
                                                          @Valid @RequestBody FamilyRelationshipUpdateRequest familyRelationship,
                                                          BindingResult bindingResult,
                                                          HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String accept = request.getHeader("Accept");
        if(!accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }
        return familyRelationshipService.updateFamilyRelationship(serialNumber, familySerialNumber, familyRelationship);
    }

    @DeleteMapping("/{familySerialNumber}")
    public void deleteFamilyRelationship(@PathVariable("serialNumber") Integer serialNumber,
                                         @PathVariable("familySerialNumber") Integer familySerialNumber) {
        familyRelationshipService.deleteFamilyRelationship(serialNumber, familySerialNumber);
    }
}
