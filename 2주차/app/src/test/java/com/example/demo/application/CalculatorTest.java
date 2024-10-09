package com.example.demo.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.infrastructure.Calculation;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private CalculationRepository calculationRepository;
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculationRepository = mock(CalculationRepository.class);

        List<Calculation> calculations = List.of(
            new Calculation("+", 1, 2, 3)
        );
        when(calculationRepository.getAll()).thenReturn(calculations);

        calculator = new Calculator(calculationRepository);
    }

    @Test
    void plus() {
        Calculation calculate = calculator.calculate(10, 2, "+");

        assertThat(calculate.getNumber1()).isEqualTo(10);
        assertThat(calculate.getNumber2()).isEqualTo(2);
        assertThat(calculate.getOperator()).isEqualTo("+");
        assertThat(calculate.getResult()).isEqualTo(12);

        verify(calculationRepository).add(any());
    }

    @Test
    void minus() {
        Calculation calculate = calculator.calculate(10, 2, "-");

        assertThat(calculate.getNumber1()).isEqualTo(10);
        assertThat(calculate.getNumber2()).isEqualTo(2);
        assertThat(calculate.getOperator()).isEqualTo("-");
        assertThat(calculate.getResult()).isEqualTo(8);

        verify(calculationRepository).add(any());
    }


    @Test
    void multiply() {
        Calculation calculate = calculator.calculate(10, 2, "*");

        assertThat(calculate.getNumber1()).isEqualTo(10);
        assertThat(calculate.getNumber2()).isEqualTo(2);
        assertThat(calculate.getOperator()).isEqualTo("*");
        assertThat(calculate.getResult()).isEqualTo(20);

        verify(calculationRepository).add(any());
    }

    @Test
    void divide() {
        Calculation calculate = calculator.calculate(10, 2, "/");

        assertThat(calculate.getNumber1()).isEqualTo(10);
        assertThat(calculate.getNumber2()).isEqualTo(2);
        assertThat(calculate.getOperator()).isEqualTo("/");
        assertThat(calculate.getResult()).isEqualTo(5);

        verify(calculationRepository).add(any());
    }

    @Test
    void divideByZero() {
        assertThatThrownBy(() -> calculator.calculate(1, 0, "/"));

        verify(calculationRepository, never()).add(any());
    }

    @Test
    void getAll() {
        List<Calculation> calculationList = calculator.getCalculationList();

        assertThat(calculationList).hasSize(1);
    }
}
