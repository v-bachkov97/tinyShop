package com.example.tinyshop.service;

import com.example.tinyshop.model.entity.Category;
import com.example.tinyshop.model.entity.Role;
import com.example.tinyshop.model.enums.CategoryType;
import com.example.tinyshop.model.enums.GenderCategory;
import com.example.tinyshop.model.enums.RolesEnum;
import com.example.tinyshop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void initializeCategories() {
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryType.values()).forEach(currentCategory -> {
                Category category = new Category();
                category.setName(currentCategory);
                categoryRepository.save(category);
            });
        }
    }
}
