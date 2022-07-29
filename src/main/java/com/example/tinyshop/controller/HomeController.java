package com.example.tinyshop.controller;

import com.example.tinyshop.model.view.LatestProductsView;
import com.example.tinyshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {

        List<LatestProductsView> men = productService.getLatestProducts(1);
        List<LatestProductsView> women = productService.getLatestProducts(2);
        List<LatestProductsView> kids = productService.getLatestProducts(3);
        model.addAttribute("men",men);
        model.addAttribute("women",women);
        model.addAttribute("kids",kids);


        return "index";
    }

}
