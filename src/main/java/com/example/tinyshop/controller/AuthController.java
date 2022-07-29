package com.example.tinyshop.controller;

import com.example.tinyshop.model.dtos.UserRegistrationDTO;
import com.example.tinyshop.service.UserService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {


        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO userRegistrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);
            return "redirect:/register";
        }
        if (!userRegistrationDTO.passwordsMatch()) {
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("passwordsDontMatch", true);

            return "redirect:/register";
        }
        if (userService.isEmailTaken(userRegistrationDTO.getEmail())) {
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("emailTaken", true);

            return "redirect:/register";
        }
        userService.register(userRegistrationDTO);

        return "redirect:/";

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("loginFailed", true);
        redirectAttributes.addFlashAttribute("username", username);

        return "redirect:/login";
    }


    @ModelAttribute
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }

}
