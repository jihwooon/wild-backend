package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void plus() {
        Calculation calculate = calculator.calculate(10, 2, "+");

        assertThat(calculate.getNumber1()).isEqualTo(10);
        assertThat(calculate.getNumber2()).isEqualTo(2);
        assertThat(calculate.getOperator()).isEqualTo("+");
        assertThat(calculate.getResult()).isEqualTo(12);

        assertThat(calculationRepository.isAdded()).isTrue();
    }

    @Test
    void minus() {
        Calculation calculate = calculator.calculate(10, 2, "-");

        assertThat(calculate.getNumber1()).isEqualTo(10);
        assertThat(calculate.getNumber2()).isEqualTo(2);
        assertThat(calculate.getOperator()).isEqualTo("-");
        assertThat(calculate.getResult()).isEqualTo(8);

        assertThat(calculationRepository.isAdded()).isTrue();
    }


    @Test
    void multiply() {
        Calculation calculate = calculator.calculate(10, 2, "*");

        assertThat(calculate.getNumber1()).isEqualTo(10);
        assertThat(calculate.getNumber2()).isEqualTo(2);
        assertThat(calculate.getOperator()).isEqualTo("*");
        assertThat(calculate.getResult()).isEqualTo(20);

        assertThat(calculationRepository.isAdded()).isTrue();
    }

    @Test
    void divide() {
        Calculation calculate = calculator.calculate(10, 2, "/");

        assertThat(calculate.getNumber1()).isEqualTo(10);
        assertThat(calculate.getNumber2()).isEqualTo(2);
        assertThat(calculate.getOperator()).isEqualTo("/");
        assertThat(calculate.getResult()).isEqualTo(5);

        assertThat(calculationRepository.isAdded()).isTrue();
    }

    @Test
    void divideByZero() {
        assertThatThrownBy(() -> calculator.calculate(1, 0, "/"));
        assertThat(calculationRepository.isAdded()).isFalse();
    }
}
