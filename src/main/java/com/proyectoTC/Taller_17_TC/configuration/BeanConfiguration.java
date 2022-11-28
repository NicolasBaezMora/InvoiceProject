package com.proyectoTC.Taller_17_TC.configuration;

import com.proyectoTC.Taller_17_TC.converters.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public InvoiceConverter getInvoiceConverter() {
        return new InvoiceConverter();
    }

    @Bean
    public ClientConverter getClientConverter() {
        return new ClientConverter();
    }

    @Bean
    public WalletConverter getWalletConverter() {
        return new WalletConverter();
    }

    @Bean
    public AgreementTypeConverter getAgreementTypeConverter() {
        return new AgreementTypeConverter();
    }

    @Bean
    public BranchOfficeConverter getBranchOfficeConverter() {
        return new BranchOfficeConverter();
    }

    @Bean
    public CommissionConverter getCommissionConverter() {
        return new CommissionConverter();
    }

    @Bean
    public PaymentConverter getPaymentConverter() {
        return new PaymentConverter();
    }

    @Bean
    public StateInvoiceConverter getStateInvoiceConverter() {
        return new StateInvoiceConverter();
    }

    @Bean
    public StatePaymentConverter getStatePaymentConverter() {
        return new StatePaymentConverter();
    }
}
