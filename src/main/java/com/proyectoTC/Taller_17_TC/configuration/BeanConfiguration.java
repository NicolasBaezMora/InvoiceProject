package com.proyectoTC.Taller_17_TC.configuration;

import com.proyectoTC.Taller_17_TC.converters.InvoiceConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public InvoiceConverter getInvoiceConverter() {
        return new InvoiceConverter();
    }

}
