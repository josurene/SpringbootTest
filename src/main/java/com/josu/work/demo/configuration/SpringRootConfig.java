package com.josu.work.demo.configuration;

import com.josu.work.demo.domain.ProductDomain;
import com.josu.work.demo.domain.implementation.ProductDomainImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringRootConfig {

    @Bean
    public ProductDomain getProductDomain(){
        return new ProductDomainImpl();
    }

}
