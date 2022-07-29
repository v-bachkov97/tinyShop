package com.example.tinyshop.service;

import com.example.tinyshop.exception.ProductNotFoundException;
import com.example.tinyshop.model.dtos.AllProductsDTO;
import com.example.tinyshop.model.entity.Product;
import com.example.tinyshop.model.view.LatestProductsView;
import com.example.tinyshop.model.view.SingleProductView;
import com.example.tinyshop.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    public List<AllProductsDTO> findAllProducts(long categoryId, long collectionId) {

        List<Product> products = productRepository.findAllByCategoryIdAndCollectionId(categoryId, collectionId);

        return products.stream().map(product -> modelMapper.map(product, AllProductsDTO.class)).toList();

    }


    public SingleProductView findProductById(long id)throws ProductNotFoundException {

        Product product = productRepository.findById(id).orElseThrow(()->
                new ProductNotFoundException("Product with id " + id + " not found."));


        return modelMapper.map(product, SingleProductView.class);
    }

    public List<LatestProductsView> getLatestProducts(long categoryId) {

        List<Product> men = productRepository.findTop5ByCategoryIdOrderByAddedOn(categoryId);

        return men.stream().map(product->modelMapper.map(product,LatestProductsView.class)).toList();
    }


}
