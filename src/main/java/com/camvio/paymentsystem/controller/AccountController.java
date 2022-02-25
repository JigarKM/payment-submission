package com.camvio.paymentsystem.controller;

import com.camvio.paymentsystem.model.PaymentRequest;
import com.camvio.paymentsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/account/{accountNumber}")
    public String displayBalance(@PathVariable String accountNumber){
         accountService.displayBalance(accountNumber);
         return "demo";
    }

    @PostMapping("/account/payment")
    public ResponseEntity<String> payBalance(@RequestBody PaymentRequest paymentRequest){
        return accountService.payBalance(paymentRequest);
    }
}
