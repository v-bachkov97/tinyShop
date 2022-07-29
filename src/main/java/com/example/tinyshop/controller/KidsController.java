package com.example.tinyshop.controller;

import com.example.tinyshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KidsController {
    private final ProductService productService;

    public KidsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("collections/kids")
    public String collections(){
        return "kids";
    }
    @GetMapping("/collections/kids/shirts")
    public String tShirts(Model model) {

        model.addAttribute("shirts", productService.findAllProducts(3, 1));

        return "kids/shirts";
    }


    @GetMapping("/collections/kids/hoodies")
    public String suits(Model model) {

        model.addAttribute("hoodies", productService.findAllProducts(3, 5));

        return "kids/hoodies";
    }

    @GetMapping("/collections/kids/shorts")
    public String shorts(Model model) {

        model.addAttribute("shorts", productService.findAllProducts(3, 3));

        return "kids/shorts";
    }

    @GetMapping("/collections/kids/accessories")
    public String accessories(Model model) {

        model.addAttribute("accessories", productService.findAllProducts(3, 4));

        return "kids/accessories";
    }
}
