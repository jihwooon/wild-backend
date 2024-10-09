package com.example.demo.presentation;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.application.CalculationRepository;
import com.example.demo.application.Calculator;
import com.example.demo.infrastructure.Calculation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CalculationCreateController.class)
class CalculationCreateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Calculator calculator;

    @MockBean
    private CalculationRepository calculationRepository;

    @Test
    void create() throws Exception {
        when(calculator.calculate(10, 2, "+")).thenReturn(
            new Calculation("+", 10, 2, 12)
        );

        String json = "{\n"
            + "    \"number1\": 10,\n"
            + "    \"number2\": 2,\n"
            + "    \"operator\": \"+\"\n"
            + "}\n";

        String result = "{\"number1\":10,\"number2\":2,\"operator\":\"+\",\"result\":12}";

        mockMvc.perform(post("/calculations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            )
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content()
                .string(containsString(result)));
    }
}
