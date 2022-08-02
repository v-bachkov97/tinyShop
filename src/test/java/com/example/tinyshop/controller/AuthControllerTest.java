package com.example.tinyshop.controller;

import com.example.tinyshop.model.entity.Cart;
import com.example.tinyshop.model.entity.Role;
import com.example.tinyshop.model.entity.User;
import com.example.tinyshop.model.enums.GenderCategory;
import com.example.tinyshop.model.enums.RolesEnum;
import com.example.tinyshop.repository.RoleRepository;
import com.example.tinyshop.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.test.web.servlet.MockMvc;


import javax.servlet.http.Cookie;
import java.time.LocalDate;
import java.util.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @MockBean
    JavaMailSender javaMailSender;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @BeforeEach
    public void setup() {
        Role roleAdmin = new Role();
        Role roleUser = new Role();
        Role roleModerator = new Role();
        roleUser.setName(RolesEnum.USER);
        roleAdmin.setName(RolesEnum.ADMIN);
        roleModerator.setName(RolesEnum.MODERATOR);
        roleRepository.saveAll(Set.of(roleAdmin,roleUser,roleModerator));

        User user = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);

        Cart cart = new Cart();

        user.setFullName("Peter Hook");
        user.setEmail("peter@abv.bg");
        user.setPassword("chelsea97");
        user.setGender(GenderCategory.MALE);
        user.setBirthDate(LocalDate.of(2011, 11, 11));
        user.setRoles(roles);
        user.setCart(cart);

        userRepository.save(user);

    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void testLoginPage() throws Exception {
        mockMvc.perform(
                        get("/login")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("login"));
    }


    @Test
    void testRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    public void loginError() throws Exception {
        mockMvc.perform(post("/login-error")
                        .param("email", "t")
                        .param("password", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }



    @Test
    void testRegistration() throws Exception {

        userRepository.deleteAll();
        mockMvc.perform((post("/register")
                        .param("email", "testuser@abv.bg")
                        .param("fullName", "Test User")
                        .param("gender", GenderCategory.MALE.name())
                        .param("birthDate", ("2001-11-11"))
                        .param("password", "chelsea97"))
                        .param("confirmPassword", "chelsea97")
                        .cookie(new Cookie("lang", Locale.ENGLISH.getLanguage())).
                        with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).
                andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Optional<User> user = userRepository.findByEmail("testuser@abv.bg");

        Assertions.assertTrue(user.isPresent());

        User newUser = user.get();

        Assertions.assertEquals("Test User", newUser.getFullName());
        Assertions.assertEquals("testuser@abv.bg", newUser.getEmail());


    }

    @Test
    public void register_InvalidInput() throws Exception {
        mockMvc.perform(post("/register")
                        .param("email", "peter@abv.bg")
                        .param("fullName", "Peter Hook")
                        .param("gender", GenderCategory.MALE.name())
                        .param("birthDate", ("2001-11-11"))
                        .param("password", "123")
                        .param("confirmPassword", "123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"))
                .andExpect(flash().attributeExists("userRegistrationDTO"));
    }

    @Test
    public void register_NotMatchingPasswords() throws Exception {
        mockMvc.perform(post("/register")
                        .param("email", "greg@abv.bg")
                        .param("fullName", "Greg Smith")
                        .param("gender", GenderCategory.MALE.name())
                        .param("birthDate", ("2001-11-11"))
                        .param("password", "1234567")
                        .param("confirmPassword", "7654321")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"))
                .andExpect(flash().attribute("passwordsDontMatch", true));
    }

    @Test
    public void register_EmailExist() throws Exception {
        mockMvc.perform(post("/register")
                        .param("email", "peter@abv.bg")
                        .param("fullName", "Peter Hook")
                        .param("gender", GenderCategory.MALE.name())
                        .param("birthDate", ("2001-11-11"))
                        .param("password", "1234567")
                        .param("confirmPassword", "1234567")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"))
                .andExpect(flash().attribute("emailTaken", true));
    }


}
