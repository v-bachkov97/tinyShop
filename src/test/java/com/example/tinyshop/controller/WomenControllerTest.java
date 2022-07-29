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
public class WomenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JavaMailSender javaMailSender;

    @Test
    void testWomenController() throws Exception {
        mockMvc.perform(
                        get("/collections/women")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("women"));
    }

    @Test
    void testWomenShirts() throws Exception {
        mockMvc.perform(
                        get("/collections/women/shirts")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("women/shirts")).
                andExpect(model().attributeExists("shirts"));
    }

    @Test
    void testWomenShorts() throws Exception {
        mockMvc.perform(
                        get("/collections/women/shorts")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("women/shorts")).
                andExpect(model().attributeExists("shorts"));
    }

    @Test
    void testWomenSuits() throws Exception {
        mockMvc.perform(
                        get("/collections/women/suits")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("women/suits")).
                andExpect(model().attributeExists("suits"));
    }

    @Test
    void testWomenAccessories() throws Exception {
        mockMvc.perform(
                        get("/collections/women/accessories")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("women/accessories")).
                andExpect(model().attributeExists("accessories"));
    }

}
