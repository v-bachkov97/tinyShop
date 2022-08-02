package com.example.tinyshop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ModeratorControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JavaMailSender javaMailSender;


    @Test
    void testAccessWithoutUser() throws Exception {

        mockMvc.perform(get("/moderator-panel")).
                andExpect(status().is3xxRedirection());
    }


}
