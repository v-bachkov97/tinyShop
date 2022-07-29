package com.example.tinyshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TinyShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinyShopApplication.class, args);
    }

}
