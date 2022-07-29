package com.example.tinyshop.controller;

import com.example.tinyshop.model.dtos.ContactFormDTO;
import com.example.tinyshop.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URI;


@Controller
public class ContactsController {
    private final ContactService contactService;

    public ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }

    @ModelAttribute
    public ContactFormDTO initContactFormDTO() {
        return new ContactFormDTO();
    }

    @GetMapping("/contacts")
    public String contacts() {

        return "/contacts";
    }

    @PostMapping("/contacts")
    public ResponseEntity<Void> sendEmail(ContactFormDTO contactFormDTO, @AuthenticationPrincipal UserDetails userDetails) throws UnsupportedEncodingException {
        this.contactService.sendMessage(contactFormDTO,userDetails);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:8080/")).build();
    }

}
