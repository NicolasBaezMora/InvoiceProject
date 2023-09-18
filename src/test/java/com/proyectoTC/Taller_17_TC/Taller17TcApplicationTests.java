package com.proyectoTC.Taller_17_TC;

import com.proyectoTC.Taller_17_TC.exceptions.NoDataFoundException;
import com.proyectoTC.Taller_17_TC.exceptions.ValidateServiceException;
import com.proyectoTC.Taller_17_TC.models.Invoice;
import com.proyectoTC.Taller_17_TC.models.StateInvoice;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import com.proyectoTC.Taller_17_TC.services.PaymentService;
import com.proyectoTC.Taller_17_TC.utils.PaymentType;
import com.proyectoTC.Taller_17_TC.validators.InvoiceValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class Taller17TcApplicationTests {

	@Autowired
	private InvoiceValidator invoiceValidator;

	@Autowired
	private PaymentService paymentService;

	@Test
	void itShouldReturnPaymentType() {
		int valueType = 1;

		String result = PaymentType.getTypePay(valueType);

		assertThat(result).isEqualTo("EFECTIVO");
	}

	@Test
	void itShouldValidateInvoiceObject() {
		Invoice invoice = Invoice.builder()
				.invoicedValue(100000D)
				.invoicedDate("2022-12-18")
				.wallet(Wallet.builder().id(0L).build())
				.stateInvoice(StateInvoice.builder().id(1L).build())
				.build();

		try {
			invoiceValidator.saveInvoice(invoice);
		} catch (Exception e) {
			assertThat(e.getClass()).hasSameClassAs(NoDataFoundException.class);
		}

	}


	@Test
	void itShouldReturnPayments() {
		assertThat(paymentService.getAllConsistentPayments(Pageable.ofSize(10), "HASH56878985")).isNotNull();
	}

}
