package com.nhnacademy.springboot.familyProject.controller;

import com.nhnacademy.springboot.familyProject.domain.DeathReportDto;
import com.nhnacademy.springboot.familyProject.domain.DeathReportRequest;
import com.nhnacademy.springboot.familyProject.exception.AcceptHeaderNotValidException;
import com.nhnacademy.springboot.familyProject.exception.ValidationFailedException;
import com.nhnacademy.springboot.familyProject.service.DeathReportService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/residents/{serialNumber}/death")
public class DeathReportRestController {
    private final DeathReportService deathReportService;

    public DeathReportRestController(DeathReportService deathReportService) {
        this.deathReportService = deathReportService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeathReportDto createDeathReport(@PathVariable("serialNumber") Integer serialNumber,
                                            @Valid @RequestBody DeathReportRequest deathReportRequest,
                                            BindingResult bindingResult,
                                            HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String accept = request.getHeader("Accept");
        if(!accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        return deathReportService.createDeathReport(serialNumber, deathReportRequest);
    }

    @PutMapping("/{targetSerialNumber}")
    public DeathReportDto updateDeathReport(@PathVariable("serialNumber") Integer serialNumber,
                                            @PathVariable("targetSerialNumber") Integer targetSerialNumber,
                                            @Valid @RequestBody DeathReportRequest deathReportRequest,
                                            BindingResult bindingResult,
                                            HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String accept = request.getHeader("Accept");
        if(!accept.equals("application/json")) {
            throw new AcceptHeaderNotValidException(accept);
        }

        return deathReportService.updateDeathReport(serialNumber, targetSerialNumber, deathReportRequest);
    }

    @DeleteMapping("/{targetSerialNumber}")
    public void deleteDeathReport(@PathVariable("serialNumber") Integer serialNumber,
                                            @PathVariable("targetSerialNumber") Integer targetSerialNumber) {
        deathReportService.deleteDeathReport(serialNumber, targetSerialNumber);
    }
}
