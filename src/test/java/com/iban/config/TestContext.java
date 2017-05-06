package com.iban.config;

import com.iban.service.IbanService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {

    @Bean
    public IbanService todoService() {
        return Mockito.mock(IbanService.class);
    }
}
