package com.example.tinyshop.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

public class ContactsControllerTest {

    @Autowired
    MockMvc mockMvc;


    @MockBean
    private JavaMailSender javaMailSender;


    @BeforeEach
    void setup(){

    }

    @Test
    public void testContactsPageWithoutUser() throws Exception {
        mockMvc.perform(get("/contacts")).
                andExpect(status().is3xxRedirection());
    }
    @Test
    @WithMockUser
    public void testAdminAccess() throws Exception {

        mockMvc.perform(get("/contacts")).
                andExpect(status().isOk());
    }



}
