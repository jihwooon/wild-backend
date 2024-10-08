package com.example.demo;

import com.example.demo.application.CalculationRepository;
import com.example.demo.infrastructure.Calculation;
import java.util.List;

public class FakeCalculationRepository implements CalculationRepository {

    @Override
    public void add(Calculation calculation) {

    }

    @Override
    public List<Calculation> getAll() {
        return List.of();
    }
}
