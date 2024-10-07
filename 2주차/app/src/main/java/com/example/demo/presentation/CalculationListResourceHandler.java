package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.infrastructure.Calculation;
import com.example.demo.presentation.dto.CalculationListRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class CalculationListResourceHandler extends ResourceMethodHandler {
    public final static String KEY = "GET /calculations";

    private final Calculator calculator = new Calculator();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String handle(String content) throws IOException {
        List<Calculation> calculations = calculator.getCalculationList();
        calculator.getCalculationList();

        return objectMapper.writeValueAsString(
                CalculationListRequestDto.of(calculations)
        );
    }
}
