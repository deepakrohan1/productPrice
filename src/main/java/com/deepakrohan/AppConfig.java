package com.deepakrohan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.deepakrohan.controller.ProductController;
import com.deepakrohan.repo.CurrencyInterface;
import com.deepakrohan.service.CurrencyService;

@SpringBootApplication(scanBasePackages= {"com.deepakrohan"})
public class AppConfig {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppConfig.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    public CurrencyInterface getCurrencyService() {
        return new CurrencyService();
    }
    
}