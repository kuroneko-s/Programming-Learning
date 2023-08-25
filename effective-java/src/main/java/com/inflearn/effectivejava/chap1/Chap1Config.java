package com.inflearn.effectivejava.chap1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Chap1Config {
    @Bean
    public HelloService helloService() {
        return new DefaultHelloService(OrderStatus.SHIPPING);
    }
}
