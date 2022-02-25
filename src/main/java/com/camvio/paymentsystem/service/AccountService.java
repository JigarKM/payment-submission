package com.camvio.paymentsystem.service;

import com.camvio.paymentsystem.model.PaymentRequest;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<String> displayBalance(String accountNumber);

    ResponseEntity<String> payBalance(PaymentRequest paymentRequest);
}
