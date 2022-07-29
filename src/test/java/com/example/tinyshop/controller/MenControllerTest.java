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

@SpringBootTest
@AutoConfigureMockMvc
public class MenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JavaMailSender javaMailSender;


    @Test
    void testMenController() throws Exception {
        mockMvc.perform(
                        get("/collections/men")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("men"));
    }

    @Test
    void testMenShirts() throws Exception {
        mockMvc.perform(
                        get("/collections/men/shirts")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("men/shirts")).
                andExpect(model().attributeExists("shirts"));
    }

    @Test
    void testMenShorts() throws Exception {
        mockMvc.perform(
                        get("/collections/men/shorts")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("men/shorts")).
                andExpect(model().attributeExists("shorts"));
    }

    @Test
    void testMenSuits() throws Exception {
        mockMvc.perform(
                        get("/collections/men/suits")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("men/suits")).
                andExpect(model().attributeExists("suits"));
    }

    @Test
    void testMenAccessories() throws Exception {
        mockMvc.perform(
                        get("/collections/men/accessories")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("men/accessories")).
                andExpect(model().attributeExists("accessories"));
    }

}
