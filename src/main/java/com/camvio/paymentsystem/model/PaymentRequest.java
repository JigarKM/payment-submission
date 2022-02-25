package com.camvio.paymentsystem.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private int paymentAmount;
    private String paymentOption;
    private int locationId;
    private String reference;

}
