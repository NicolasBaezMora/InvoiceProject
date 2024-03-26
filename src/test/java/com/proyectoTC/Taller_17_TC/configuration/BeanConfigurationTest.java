package com.proyectoTC.Taller_17_TC.configuration;

import com.proyectoTC.Taller_17_TC.converters.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanConfigurationTest {

    @Autowired
    private InvoiceConverter invoiceConverter;

    @Autowired
    private ClientConverter clientConverter;

    @Autowired
    private WalletConverter walletConverter;

    @Autowired
    private AgreementTypeConverter agreementTypeConverter;

    @Autowired
    private BranchOfficeConverter branchOfficeConverter;

    @Autowired
    private CommissionConverter commissionConverter;

    @Autowired
    private PaymentConverter paymentConverter;

    @Autowired
    private StateInvoiceConverter stateInvoiceConverter;

    @Autowired
    private StatePaymentConverter statePaymentConverter;

    @Test
    public void testBeansCreation() {
        assertNotNull(invoiceConverter);
        assertNotNull(clientConverter);
        assertNotNull(walletConverter);
        assertNotNull(agreementTypeConverter);
        assertNotNull(branchOfficeConverter);
        assertNotNull(commissionConverter);
        assertNotNull(paymentConverter);
        assertNotNull(stateInvoiceConverter);
        assertNotNull(statePaymentConverter);
    }
}
