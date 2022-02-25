package com.camvio.paymentsystem.controller;

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

//    public String viewBalance(Model model){
//        model.addAttribute("balance", )
//    }
    @Autowired
    AccountService accountService;

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<String> displayBalance(@PathVariable String accountNumber){
        return accountService.displayBalance(accountNumber);
    }

    @PostMapping("/account")
    public ResponseEntity<String> payBalance(@RequestBody String accountNumber){
        return accountService.displayBalance(accountNumber);
    }
}
