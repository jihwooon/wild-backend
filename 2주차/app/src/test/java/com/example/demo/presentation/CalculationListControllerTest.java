package com.example.demo.presentation;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.application.CalculationRepository;
import com.example.demo.application.Calculator;
import com.example.demo.infrastructure.Calculation;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CalculationListController.class)
class CalculationListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        given(calculator.getCalculationList()).willReturn(List.of(new Calculation("+", 10, 2, 12)));
    }

    @Test
    void home() throws Exception {
        String result = "{\"number1\":10,\"number2\":2,\"operator\":\"+\",\"result\":12}";

        mockMvc.perform(get("/calculations"))
            .andExpect(status().isOk())
            .andExpect(content()
                .string(containsString(result)));
    }
}
