package com.example.tinyshop.controller;

import com.example.tinyshop.model.entity.*;
import com.example.tinyshop.model.enums.GenderCategory;
import com.example.tinyshop.model.enums.RolesEnum;
import com.example.tinyshop.model.user.TinyShopUserDetails;
import com.example.tinyshop.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {

    @MockBean
    JavaMailSender javaMailSender;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private TinyShopUserDetails userDetails;

    private User testUser;

    @BeforeEach
    public void setUp() {

        Role adminRole = new Role();
        adminRole.setName(RolesEnum.ADMIN);
        roleRepository.save(adminRole);

        Cart cart = new Cart();
        cart.setProducts(List.of(initProduct()));

        User user = new User();
        user.setFullName("Test Test");
        user.setPassword("chelsea97");
        user.setGender(GenderCategory.MALE);
        user.setBirthDate(LocalDate.of(1997, 12, 18));
        user.setEmail("test@abv.bg");
        user.setRoles(Set.of(adminRole));
        user.setCart(cart);

        testUser = userRepository.save(user);

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_".concat(r.getName().name())))
                .collect(Collectors.toList());

        this.userDetails = new TinyShopUserDetails(user.getPassword(), user.getEmail(), user.getFullName(), cart, user.getId(), authorities);


    }

    @AfterEach
    void tearDown() {
        cartRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    public void testCartView() throws Exception {

        mockMvc.perform(get("/profile/cart").with(user(userDetails))).
                andExpect(status().isOk()).andExpect(view().name("cart"));
    }


    @Test
    void testAddToCart() throws Exception {

        Assertions.assertEquals(1, userDetails.getCart().getProducts().size());

        Product newProduct = new Product();

        Optional<Collection> collection = collectionRepository.findById(1L);
        Optional<Category> category = categoryRepository.findById(1L);

        newProduct.setCollection(collection.get());
        newProduct.setCategory(category.get());
        newProduct.setName("Test T-Shirt2");
        newProduct.setBrand("Test Brand2");
        newProduct.setAddedOn(LocalDate.now());
        newProduct.setFabric("testFabric");
        newProduct.setColor("testColor");
        newProduct.setPrice(BigDecimal.valueOf(20));
        newProduct.setPath("/images/images/men/shirts/polo-02.jpg");
        newProduct.setSize("L");

        productRepository.save(newProduct);


        mockMvc.perform(post("/cart/add/" + newProduct.getId()).with(user(userDetails))
                        .with(csrf()))
                .andExpect(redirectedUrl("/profile/cart"));

        Optional<Cart> cart = cartRepository.findById(userDetails.getCart().getId());

        Assertions.assertEquals(2, cart.get().getProducts().size());
    }

    @Test
    void testRemoveFromCart() throws Exception {
        Assertions.assertEquals(1, userDetails.getCart().getProducts().size());


        mockMvc.perform(post("/cart/remove/" + userDetails.getCart().getProducts().get(0).getId()).with(user(userDetails))
                        .with(csrf()))
                .andExpect(redirectedUrl("/profile/cart"));

        Optional<Cart> cart = cartRepository.findById(userDetails.getCart().getId());

        Assertions.assertEquals(0, cart.get().getProducts().size());
    }

    @Test
    void testCheckoutWithoutAddress() throws Exception {

        mockMvc.perform(post("/cart/checkout").with(user(userDetails)).with(csrf()))
                .andExpect(redirectedUrl("/profile/address-details"));

    }


    private Product initProduct() {
        Product product = new Product();
        Optional<Collection> collection = collectionRepository.findById(1L);
        Optional<Category> category = categoryRepository.findById(1L);

        product.setCollection(collection.get());
        product.setCategory(category.get());
        product.setName("Test T-Shirt");
        product.setBrand("Test Brand");
        product.setAddedOn(LocalDate.now());
        product.setFabric("testFabric");
        product.setColor("testColor");
        product.setPrice(BigDecimal.valueOf(10));
        product.setPath("/images/images/men/shirts/polo-01.jpg");
        product.setSize("L");


        return productRepository.save(product);
    }

}
