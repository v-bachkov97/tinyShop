package com.example.tinyshop.controller;

import com.example.tinyshop.model.dtos.AddressDTO;
import com.example.tinyshop.service.AddressService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/profile/address-details")
    public String address() {
        return "address-view";
    }

    @PostMapping("/profile/address-details")
    public String saveAddress(@Valid AddressDTO addressDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addressDTO", addressDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addressDTO", bindingResult);
            return "redirect:/profile/address-details";
        }
        if (addressService.isRegistered(userDetails)){
            return "redirect:/profile/cart";
        }
        if (addressService.isRegistered(userDetails)){
            return "redirect:/profile/cart";
        }
        addressService.saveAddress(userDetails.getUsername(), addressDTO);
        return "redirect:/profile/cart";
    }

    @ModelAttribute
    public AddressDTO initAddressDTO() {
        return new AddressDTO();
    }
}
