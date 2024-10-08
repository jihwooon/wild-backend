package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.application.Calculator;
import com.example.demo.infrastructure.Calculation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    FakeCalculationRepository calculationRepository;
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculationRepository = new FakeCalculationRepository();
        calculator = new Calculator(calculationRepository);
    }

    @Test
    void creation() {
        Calculation calculate = calculator.calculate(1, 2, "+");
        assertThat(calculate.getResult()).isEqualTo(3);
    }

    @Test
    void multiply() {
        Calculation calculate = calculator.calculate(1, 2, "*");
        assertThat(calculate.getResult()).isEqualTo(2);
    }

    @Test
    void minus() {
        Calculation calculate = calculator.calculate(1, 2, "-");
        assertThat(calculate.getResult()).isEqualTo(-1);
    }
}
