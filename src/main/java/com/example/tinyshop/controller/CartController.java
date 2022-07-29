package com.example.tinyshop.controller;

import com.example.tinyshop.exception.ProductNotFoundException;
import com.example.tinyshop.model.user.TinyShopUserDetails;
import com.example.tinyshop.model.view.ProductCartView;
import com.example.tinyshop.service.CartService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/profile/cart")
    public String cart(Model model, @AuthenticationPrincipal TinyShopUserDetails userDetails) {


        List<ProductCartView> products = cartService.getProducts(userDetails.getUsername());
        BigDecimal totalPrice = cartService.findTotalPrice(products);
        boolean isEmpty = products.isEmpty();
        int count = products.size();
        model.addAttribute("products", products);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("count", count);
        model.addAttribute("isEmpty",isEmpty);


        return "cart";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails)throws ProductNotFoundException {

        if (userDetails == null) {
            return "redirect:/login";
        }
        cartService.addProduct(id, userDetails.getUsername());

        return "redirect:/profile/cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails) throws ProductNotFoundException {
        cartService.removeItem(id, userDetails.getUsername());
        return "redirect:/profile/cart";
    }

    @PostMapping("/cart/checkout")
    public String checkout(@AuthenticationPrincipal UserDetails userDetails) {

        if (!cartService.makeOrder(userDetails.getUsername())){
            return "redirect:/profile/address-details";
        }

        return "redirect:/profile/order-sent";
    }

    @GetMapping("/profile/order-sent")
    public String checkoutMsg(@AuthenticationPrincipal UserDetails userDetails,Model model){

        model.addAttribute("orderDetails",cartService.getOrderDetails(userDetails.getUsername()));

        cartService.clearCart(userDetails);
        return "/order-sent";
    }


}
