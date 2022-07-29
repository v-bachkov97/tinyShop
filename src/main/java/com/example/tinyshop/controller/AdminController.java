package com.example.tinyshop.controller;

import com.example.tinyshop.model.user.TinyShopUserDetails;
import com.example.tinyshop.model.view.AdminPanelUserView;
import com.example.tinyshop.service.AdminService;
import com.example.tinyshop.service.TinyShopUserDetailsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin-panel")
    public String adminPanel(Model model, @AuthenticationPrincipal TinyShopUserDetails userDetails) {

        List<AdminPanelUserView> users = adminService.findAll(userDetails.getId());

        model.addAttribute("users", users);

        return "admin-panel";

    }

    @PostMapping("/admin-panel/{id}/promote")
    public String promote(@PathVariable long id) {
        adminService.promote(id);
        return "redirect:/admin-panel";
    }

    @PostMapping("/admin-panel/{id}/demote")
    public String demote(@PathVariable long id) {
        adminService.demote(id);
        return "redirect:/admin-panel";
    }

    @PostMapping("/admin-panel/{id}/delete")
    public String delete(@PathVariable long id) {
        adminService.deleteAccount(id);
        return "redirect:/admin-panel";
    }

}
