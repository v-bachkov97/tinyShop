package com.example.tinyshop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JavaMailSender javaMailSender;


    @Test
    @WithMockUser(username = "ventsislavbachkov@gmail.com", roles = {"ADMIN"})
    void testAccessWithAdmin() throws Exception {

        mockMvc.perform(get("/admin-panel")).
                andExpect(status().isOk()).
                andExpect(view().name("admin-panel"));
    }

}
