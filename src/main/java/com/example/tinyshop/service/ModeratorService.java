package com.example.tinyshop.service;

import com.example.tinyshop.model.dtos.AddNewProductDTO;
import com.example.tinyshop.model.entity.Category;
import com.example.tinyshop.model.entity.Collection;
import com.example.tinyshop.model.entity.Product;
import com.example.tinyshop.repository.CategoryRepository;
import com.example.tinyshop.repository.CollectionRepository;
import com.example.tinyshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ModeratorService {
    private final CategoryRepository categoryRepository;
    private final CollectionRepository collectionRepository;
    private final ProductRepository productRepository;

    public ModeratorService(CategoryRepository categoryRepository, CollectionRepository collectionRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.collectionRepository = collectionRepository;
        this.productRepository = productRepository;
    }

    public void addProduct(AddNewProductDTO addNewProductDTO) {

        Product product = new Product();
        Category category = categoryRepository.getById(addNewProductDTO.getCategory());
        Collection collection = collectionRepository.getById(addNewProductDTO.getCollection());

        product.setName(addNewProductDTO.getName());
        product.setAddedOn(LocalDate.now());
        product.setBrand(addNewProductDTO.getBrand());
        product.setCategory(category);
        product.setCollection(collection);
        product.setColor(addNewProductDTO.getColor());
        product.setFabric(addNewProductDTO.getFabric());
        product.setPath(addNewProductDTO.getPath());
        product.setSize(addNewProductDTO.getSize());
        product.setPrice(addNewProductDTO.getPrice());

        productRepository.save(product);

    }
}
