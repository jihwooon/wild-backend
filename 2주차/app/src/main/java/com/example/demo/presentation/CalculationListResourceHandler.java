package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.infrastructure.Calculation;
import com.example.demo.presentation.dto.CalculationListRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CalculationListResourceHandler extends ResourceMethodHandler {

    private final Calculator calculator;

    private final ObjectMapper objectMapper;

    public CalculationListResourceHandler(Calculator calculator, ObjectMapper objectMapper) {
        this.calculator = calculator;
        this.objectMapper = objectMapper;
    }

    @Override
    public String handle(String content) throws IOException {
        List<Calculation> calculations = calculator.getCalculationList();
        calculator.getCalculationList();

        return objectMapper.writeValueAsString(
            CalculationListRequestDto.of(calculations)
        );
    }

    @Override
    public String key() {
        return "GET /calculations";
    }
}
