package com.example.demo.presentation.dto;

import com.example.demo.infrastructure.Calculation;

import java.util.List;
import java.util.stream.Collectors;

public record CalculationListRequestDto(
        List<CalculationResponseDto> calculations) {

    public static CalculationListRequestDto of(List<Calculation> calculations) {
        return new CalculationListRequestDto(
                calculations.stream()
                        .map(calculation -> {
                                    return new CalculationResponseDto(
                                            calculation.getNumber1(),
                                            calculation.getNumber2(),
                                            calculation.getOperator(),
                                            calculation.getResult()
                                    );
                                }
                        ).collect(Collectors.toList())
        );
    }
}
