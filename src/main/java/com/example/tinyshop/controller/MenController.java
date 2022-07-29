package com.example.tinyshop.controller;

import com.example.tinyshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MenController {


    private final ProductService productService;

    public MenController(ProductService menProductService) {
        this.productService = menProductService;
    }

    @GetMapping("/collections/men")
    public String collections() {
        return "men";
    }

    @GetMapping("/collections/men/shirts")
    public String tShirts(Model model) {

        model.addAttribute("shirts", productService.findAllProducts(1, 1));

        return "men/shirts";
    }

    @GetMapping("/collections/men/suits")
    public String suits(Model model) {

        model.addAttribute("suits", productService.findAllProducts(1, 2));

        return "men/suits";
    }

    @GetMapping("/collections/men/shorts")
    public String shorts(Model model) {

        model.addAttribute("shorts", productService.findAllProducts(1, 3));

        return "men/shorts";
    }

    @GetMapping("/collections/men/accessories")
    public String accessories(Model model) {

        model.addAttribute("accessories", productService.findAllProducts(1, 4));

        return "men/accessories";
    }
}
