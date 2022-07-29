package com.example.tinyshop.controller;

import com.example.tinyshop.model.entity.Category;
import com.example.tinyshop.model.entity.Collection;
import com.example.tinyshop.model.entity.Product;
import com.example.tinyshop.model.enums.CategoryType;
import com.example.tinyshop.model.enums.CollectionCategory;
import com.example.tinyshop.repository.CategoryRepository;
import com.example.tinyshop.repository.CollectionRepository;
import com.example.tinyshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JavaMailSender javaMailSender;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    Product testProduct;


    @BeforeEach
    public void setup() {

        Product product = new Product();
        Optional<Category> category = categoryRepository.findById(1L);
        Optional<Collection> collection = collectionRepository.findById(1L);

        product.setPrice(BigDecimal.valueOf(19.90));
        product.setSize("L");
        product.setName("Test");
        product.setColor("green");
        product.setFabric("polyester");
        product.setBrand("Test Brand");
        product.setAddedOn(LocalDate.now());
        product.setCategory(category.get());
        product.setCollection(collection.get());
        product.setPath("/images/images/men/t-shirts/jack-1.jpg");

        productRepository.save(product);
        testProduct = product;

    }

    @Test
    void testProductView() throws Exception {
        long productId = testProduct.getId();
        mockMvc.perform(
                        get("/collections/products/" + productId + "/details")
                ).
                andExpect(status().isOk()).
                andExpect(view().name("single-product")).
                andExpect(model().attributeExists("view"));
    }
    @Test
    void testNonExistentProduct() throws Exception {
        mockMvc.perform(
                        get("/collections/products/99999999999999999/details")
                ).
                andExpect(view().name("error")).
                andExpect(model().attributeExists("message"));
    }


}
