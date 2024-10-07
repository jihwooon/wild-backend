package com.example.demo.application;

import com.example.demo.infrastructure.Calculation;
import com.example.demo.infrastructure.CalculationRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class Calculator {
    private final CalculationRepository calculationRepository;

    private final Map<String, Operator> operators = new HashMap<>();

    public Calculator(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
        operators.put("+", new OperatorPlus());
        operators.put("-", new OperatorMinus());
        operators.put("*", new OperatorMultiply());
        operators.put("/", new OperatorDivide());
    }

    public Calculation calculate(Integer number1, Integer number2, String operatorSymbol) {
        Operator operator = operators.get(operatorSymbol);
        int result = operator.calculate(number1, number2);

        Calculation calculation = new Calculation(operatorSymbol, number1, number2, result);

        calculationRepository.add(calculation);

        return calculation;
    }

    public List<Calculation> getCalculationList() {
        return calculationRepository.getAll();
    }
}
