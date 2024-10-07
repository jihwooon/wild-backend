package com.example.demo.infrastructure;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CalculationRepository {

    private final List<Calculation> calculations = new ArrayList<>();

    private CalculationRepository() {
    }

    public void add(Calculation calculation) {
        calculations.add(calculation);
    }

    public List<Calculation> getAll() {
        return new ArrayList<>(calculations);
    }
}
