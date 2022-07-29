package com.example.tinyshop.controller;

import com.example.tinyshop.exception.ProductNotFoundException;
import com.example.tinyshop.model.dtos.AddNewProductDTO;
import com.example.tinyshop.service.ModeratorService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ModeratorController {
    private final ModeratorService moderatorService;

    public ModeratorController(ModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    @GetMapping("/moderator-panel")
    public String moderatorPanel() {

        return "moderator-panel";
    }

    @PostMapping("/moderator-panel")
    public String moderator(@Valid AddNewProductDTO addNewProductDTO, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) throws ProductNotFoundException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addNewProductDTO", addNewProductDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addNewProductDTO", bindingResult);
            return "redirect:/moderator-panel";
        }

        moderatorService.addProduct(addNewProductDTO);
        return "redirect:/";
    }

    @ModelAttribute
    private AddNewProductDTO initAddNewProductDTO() {
        return new AddNewProductDTO();
    }
}
