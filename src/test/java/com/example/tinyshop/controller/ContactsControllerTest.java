package com.example.tinyshop.controller;

import com.example.tinyshop.model.entity.Cart;
import com.example.tinyshop.model.entity.Role;
import com.example.tinyshop.model.entity.User;
import com.example.tinyshop.model.enums.GenderCategory;
import com.example.tinyshop.model.user.TinyShopUserDetails;
import com.example.tinyshop.repository.CartRepository;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

public class ContactsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CartRepository cartRepository;

    private TinyShopUserDetails userDetails;

    @MockBean
    private JavaMailSender javaMailSender;

    @BeforeEach
    public void setup() {
        userRepo.deleteAll();
        User userEntity = initUser();
        userRepo.save(userEntity);
        cartRepository.save(userEntity.getCart());

    }

    @AfterEach
    public void tearDown() {
        userRepo.deleteAll();
    }


    @Test
    @WithMockUser(roles = "USER")
    public void testContactsPage() throws Exception {
        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk());
    }

    private User initUser() {
        List<Role> roleEntities = roleRepo.findAll();
        Cart cart = new Cart();
        cart.setProducts(new ArrayList<>());

        User user = new User();
        user.setFullName("Peter Hook");
        user.setEmail("ventsislavbachkov@gmail.com");
        user.setPassword("chelsea97");
        user.setGender(GenderCategory.MALE);
        user.setBirthDate(LocalDate.of(2011, 11, 11));
        user.setRoles(new HashSet<>(roleEntities));
        user.setCart(cart);


        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_".concat(r.getName().name())))
                .collect(Collectors.toSet());

        this.userDetails = new TinyShopUserDetails(user.getPassword(), user.getEmail(),
                user.getFullName(), user.getCart(), user.getId(), authorities);

        return user;

    }

}
