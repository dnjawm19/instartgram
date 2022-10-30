package com.example.instartgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InstartgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstartgramApplication.class, args);
    }

}
