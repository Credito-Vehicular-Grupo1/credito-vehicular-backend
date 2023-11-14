package com.grupo1.creditovehicular.mapping;

import com.grupo1.creditovehicular.plan.mapping.PlanMapper;
import com.grupo1.creditovehicular.plan.mapping.ResultMapper;
import com.grupo1.creditovehicular.user.mapping.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("CreditoVehicularAPIMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public UserMapper UserMapper() {
        return new UserMapper();
    }
    @Bean
    public PlanMapper PlanMapper() {
        return new PlanMapper();
    }
    @Bean
    public ResultMapper ResultMapper() {
        return new ResultMapper();
    }
}
