package com.example.demo.presentation;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class CalculationCreateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create() throws Exception {
        String json = "{\n"
            + "    \"number1\": 0,\n"
            + "    \"number2\": 2,\n"
            + "    \"operator\": \"+\"\n"
            + "}\n";

        String result = "{\"number1\":0,\"number2\":2,\"operator\":\"+\",\"result\":2}";

        mockMvc.perform(post("/calculations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            )
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content()
                .string(containsString(result)));
    }
}
