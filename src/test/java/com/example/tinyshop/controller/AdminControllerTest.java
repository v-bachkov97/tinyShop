package com.example.tinyshop.controller;

import com.example.tinyshop.model.entity.Cart;
import com.example.tinyshop.model.entity.Role;
import com.example.tinyshop.model.entity.User;
import com.example.tinyshop.model.enums.GenderCategory;
import com.example.tinyshop.model.enums.RolesEnum;
import com.example.tinyshop.model.user.TinyShopUserDetails;
import com.example.tinyshop.repository.MessageRepository;
import com.example.tinyshop.repository.RoleRepository;
import com.example.tinyshop.repository.UserRepository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.test.web.servlet.MockMvc;



import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JavaMailSender javaMailSender;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    private TinyShopUserDetails userDetails;


    @BeforeEach
    public void setUp() {

        Role adminRole = new Role();
        adminRole.setName(RolesEnum.ADMIN);
        roleRepository.save(adminRole);


        User user = new User();
        user.setFullName("Test Test");
        user.setPassword("chelsea97");
        user.setGender(GenderCategory.MALE);
        user.setBirthDate(LocalDate.of(1997, 12, 18));
        user.setEmail("test@abv.bg");

        Cart cart = new Cart();
        user.setCart(cart);

        user.setRoles(Set.of(adminRole));

        this.userRepository.save(user);

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_".concat(r.getName().name())))
                .collect(Collectors.toList());

        this.userDetails = new TinyShopUserDetails(user.getPassword(), user.getEmail(), user.getFullName(), user.getCart(), user.getId(), authorities);


    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }


    @Test
    void testAccessWithoutUser() throws Exception {

        mockMvc.perform(get("/admin-panel")).
                andExpect(status().is3xxRedirection());
    }

    @Test
    void testAccessWithAdmin() throws Exception {

        mockMvc.perform(get("/admin-panel").with(user(userDetails))).
                andExpect(status().isOk());
    }

}
