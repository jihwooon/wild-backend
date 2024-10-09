package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.infrastructure.Calculation;
import com.example.demo.presentation.dto.CalculationListRequestDto;
import jakarta.servlet.annotation.HttpConstraint;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculations")
public class CalculationListController {

    private final Calculator calculator;

    public CalculationListController(Calculator calculator) {
        this.calculator = calculator;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CalculationListRequestDto handle() {
        List<Calculation> calculations = calculator.getCalculationList();

        return CalculationListRequestDto.of(calculations);
    }
}
