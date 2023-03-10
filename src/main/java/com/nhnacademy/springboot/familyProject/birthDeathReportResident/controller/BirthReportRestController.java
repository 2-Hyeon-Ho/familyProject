package com.nhnacademy.springboot.familyProject.birthDeathReportResident.controller;

import com.nhnacademy.springboot.familyProject.birthDeathReportResident.dto.BirthReportResponse;
import com.nhnacademy.springboot.familyProject.birthDeathReportResident.dto.BirthReportRequest;
import com.nhnacademy.springboot.familyProject.exception.AcceptHeaderNotValidException;
import com.nhnacademy.springboot.familyProject.exception.ValidationFailedException;
import com.nhnacademy.springboot.familyProject.birthDeathReportResident.service.BirthReportService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/residents/{serialNumber}/birth")
public class BirthReportRestController {
    private final BirthReportService birthReportService;

    public BirthReportRestController(BirthReportService birthReportService) {
        this.birthReportService = birthReportService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BirthReportResponse createBirthReport(@PathVariable("serialNumber") Integer serialNumber,
                                            @Valid @RequestBody BirthReportRequest birthReportRequest,
                                            BindingResult bindingResult,
                                            HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String accept = request.getHeader("Accept");
        if(!accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        return birthReportService.createBirthReport(serialNumber, birthReportRequest);
    }

    @PutMapping("/{targetSerialNumber}")
    public BirthReportResponse updateBirthReport(@PathVariable("serialNumber") Integer serialNumber,
                                            @PathVariable("targetSerialNumber") Integer targetSerialNumber,
                                            @Valid @RequestBody BirthReportRequest birthReportRequest,
                                            BindingResult bindingResult,
                                            HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String accept = request.getHeader("Accept");
        if(!accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        return birthReportService.updateBirthReport(serialNumber, targetSerialNumber, birthReportRequest);
    }

    @DeleteMapping("/{targetSerialNumber}")
    public void deleteBirthReport(@PathVariable("serialNumber") Integer serialNumber,
                                  @PathVariable("targetSerialNumber") Integer targetSerialNumber) {
        birthReportService.deleteBirthReport(serialNumber, targetSerialNumber);
    }
}
