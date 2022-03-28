package com.camvio.paymentsystem.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PaymentRequest {
	@NotBlank
	private BigDecimal paymentAmount;
	private String paymentOption;
	private int locationId;
	private String reference;

}
