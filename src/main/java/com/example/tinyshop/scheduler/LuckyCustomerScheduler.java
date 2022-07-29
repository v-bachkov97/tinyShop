package com.example.tinyshop.scheduler;

import com.example.tinyshop.model.entity.User;
import com.example.tinyshop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class LuckyCustomerScheduler {
   private final JavaMailSender mailSender;
   private final UserRepository userRepository;
   private static final Logger LOGGER = LoggerFactory.getLogger(LuckyCustomerScheduler.class);

    public LuckyCustomerScheduler(JavaMailSender javaMailSender, UserRepository userRepository) {
        this.mailSender = javaMailSender;
        this.userRepository = userRepository;
    }

    @Scheduled(cron = "0 0 14 * * ?")
    public void informCustomer(){

        long customerId = this.findRandomCustomer();
        this.sendMessage(customerId);

    }

    private void sendMessage(long customerId) {
        Optional<User> customer = userRepository.findById(customerId);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("tinyshopemailservice@gmail.com");
        simpleMailMessage.setTo(customer.get().getEmail());
        simpleMailMessage.setSubject("You are our daily lucky winner.");
        simpleMailMessage.setText(String.format("Hello, %s.\n" +
                "You just won our daily lottery, which means you will be granted a voucher, that you can use " +
                "in our 'tinyShop'. In order to receive it, simply log into your account, message us and mention the win, using the " +
                "contact-form.\n" +
                "TinyShop wishes you all the best.",customer.get().getFullName()));

        this.mailSender.send(simpleMailMessage);
        LOGGER.info("Message sent successfully at {}", LocalDateTime.now());

    }

    private long findRandomCustomer() {
        List<User> users = userRepository.findAll();
        Random random = new Random();
        return random.nextInt(users.size()+1);

    }
}
