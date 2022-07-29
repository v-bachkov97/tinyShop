package com.example.tinyshop.service;

import com.example.tinyshop.model.dtos.ContactFormDTO;
import com.example.tinyshop.model.entity.Message;
import com.example.tinyshop.model.entity.User;
import com.example.tinyshop.repository.MessageRepository;
import com.example.tinyshop.repository.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
public class ContactService {
    private final MessageRepository messageRepository;
    private final JavaMailSender mailSender;
    private final UserRepository userRepository;


    public ContactService(MessageRepository messageRepository, JavaMailSender mailSender, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.mailSender = mailSender;

        this.userRepository = userRepository;
    }

    public void sendMessage(ContactFormDTO contactFormDTO, UserDetails userDetails) throws UnsupportedEncodingException {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(String.valueOf(new InternetAddress("redacted@gmail.com", userDetails.getUsername())));
        simpleMailMessage.setTo("tinyshopemailservice@gmail.com");
        simpleMailMessage.setSubject(contactFormDTO.getSubject());
        simpleMailMessage.setText(contactFormDTO.getContent());

        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        Message message = new Message();
        message.setContent(contactFormDTO.getContent());
        message.setSubject(contactFormDTO.getSubject());
        message.setSender(user.get());

        this.mailSender.send(simpleMailMessage);

        messageRepository.save(message);

    }


}
