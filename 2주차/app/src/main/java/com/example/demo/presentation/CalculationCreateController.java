package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.infrastructure.Calculation;
import com.example.demo.presentation.dto.CalculationRequestDto;
import com.example.demo.presentation.dto.CalculationResponseDto;
import java.io.IOException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/calculations")
public class CalculationCreateController {

    private final Calculator calculator;

    public CalculationCreateController(Calculator calculator) {
        this.calculator = calculator;
    }

    @PostMapping
    public CalculationResponseDto handle(@RequestBody CalculationRequestDto calculationRequestDto)
        throws IOException {

        Calculation calculate = calculator.calculate(
            calculationRequestDto.getNumber1(),
            calculationRequestDto.getNumber2(),
            calculationRequestDto.getOperator()
        );

        return new CalculationResponseDto(
            calculate.getNumber1(),
            calculate.getNumber2(),
            calculate.getOperator(),
            calculate.getResult()
        );
    }
}
