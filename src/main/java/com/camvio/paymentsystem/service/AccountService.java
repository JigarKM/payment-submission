package com.camvio.paymentsystem.service;

import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<String> displayBalance(String accountNumber);
}
