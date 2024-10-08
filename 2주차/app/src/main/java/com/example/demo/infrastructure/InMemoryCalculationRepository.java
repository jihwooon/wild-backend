package com.example.demo.infrastructure;

import com.example.demo.application.CalculationRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class InMemoryCalculationRepository implements CalculationRepository {

    private final List<Calculation> calculations = new ArrayList<>();

    public InMemoryCalculationRepository() {
    }

    @Override
    public void add(Calculation calculation) {
        calculations.add(calculation);
    }

    @Override
    public List<Calculation> getAll() {
        return new ArrayList<>(calculations);
    }
}
