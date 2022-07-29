package com.example.tinyshop.controller;

import com.example.tinyshop.exception.ProductNotFoundException;
import com.example.tinyshop.model.view.SingleProductView;
import com.example.tinyshop.repository.ProductRepository;
import com.example.tinyshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductsController {
    private final ProductRepository productRepository;
    private final ProductService productService;

    public ProductsController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping("/collections/products/{id}/details")
    private String productView(@PathVariable long id, Model model)throws ProductNotFoundException {

        SingleProductView view = productService.findProductById(id);
        model.addAttribute("view", view);
        return "single-product";
    }
    @ExceptionHandler
    public ModelAndView handleProductNotFoundException(ProductNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

    @ModelAttribute
    private SingleProductView initSingleProductView() {
        return new SingleProductView();
    }
}
