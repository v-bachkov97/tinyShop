package com.example.tinyshop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
public class KidsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JavaMailSender javaMailSender;


    @Test
    void testKidsController() throws Exception {
        mockMvc.perform(
                        get("/collections/kids")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("kids"));
    }

    @Test
    void testKidsShirts() throws Exception {
        mockMvc.perform(
                        get("/collections/kids/shirts")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("kids/shirts")).
                andExpect(model().attributeExists("shirts"));
    }

    @Test
    void testKidsShorts() throws Exception {
        mockMvc.perform(
                        get("/collections/kids/shorts")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("kids/shorts")).
                andExpect(model().attributeExists("shorts"));
    }

    @Test
    void testKidsHoodies() throws Exception {
        mockMvc.perform(
                        get("/collections/kids/hoodies")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("kids/hoodies")).
                andExpect(model().attributeExists("hoodies"));
    }

    @Test
    void testKidsAccessories() throws Exception {
        mockMvc.perform(
                        get("/collections/kids/accessories")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("kids/accessories")).
                andExpect(model().attributeExists("accessories"));
    }

}

