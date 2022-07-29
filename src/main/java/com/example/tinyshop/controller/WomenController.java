package com.example.tinyshop.controller;

import com.example.tinyshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WomenController {

    private final ProductService productService;

    public WomenController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/collections/women")
    public String collections(){
        return "women";
    }

    @GetMapping("/collections/women/shirts")
    public String tShirts(Model model) {

        model.addAttribute("shirts", productService.findAllProducts(2, 1));

        return "women/shirts";
    }


    @GetMapping("/collections/women/suits")
    public String suits(Model model) {

        model.addAttribute("suits", productService.findAllProducts(2, 2));

        return "women/suits";
    }

    @GetMapping("/collections/women/shorts")
    public String shorts(Model model) {

        model.addAttribute("shorts", productService.findAllProducts(2, 3));

        return "women/shorts";
    }

    @GetMapping("/collections/women/accessories")
    public String accessories(Model model) {

        model.addAttribute("accessories", productService.findAllProducts(2, 4));

        return "women/accessories";
    }
}
