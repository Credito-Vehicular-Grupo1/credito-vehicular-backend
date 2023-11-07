package com.grupo1.creditovehicular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CreditoVehicularApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditoVehicularApplication.class, args);
    }

}
