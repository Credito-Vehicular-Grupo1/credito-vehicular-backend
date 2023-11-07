package com.grupo1.creditovehicular.mapping;

import com.grupo1.creditovehicular.user.mapping.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("CreditoVehicularAPIMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public UserMapper UserMapper() {
        return new UserMapper();
    }
}
