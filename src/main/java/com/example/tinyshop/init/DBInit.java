package com.example.tinyshop.init;

import com.example.tinyshop.service.CategoryService;
import com.example.tinyshop.service.CollectionService;
import com.example.tinyshop.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final UserService userService;
    private final CategoryService categoryService;
    private final CollectionService collectionService;


    public DBInit(UserService userService, CategoryService categoryService, CollectionService collectionService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.collectionService = collectionService;
    }

    @Override
    public void run(String... args) throws Exception {

        userService.initializeRoles();
        categoryService.initializeCategories();
        collectionService.initializeCollections();
    }
}
