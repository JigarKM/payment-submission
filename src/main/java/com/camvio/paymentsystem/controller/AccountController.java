package com.camvio.paymentsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.camvio.paymentsystem.model.Account;
import com.camvio.paymentsystem.model.BalanceResponse;
import com.camvio.paymentsystem.model.PaymentStatus;
import com.camvio.paymentsystem.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	AccountService accountService;

	@GetMapping("/account")
	public String displayForm(Model model) {
		model.addAttribute("account", new Account());
		model.addAttribute("balanceResponse", new BalanceResponse());
		return "account";
	}

	/**
	  Method to display Account details like balance
	 */
	@PostMapping("/details")
	public String accountDetails(@ModelAttribute Account account, Model model) {
		BalanceResponse balance = accountService.displayBalance(account.getAccountNo());
		model.addAttribute("balanceResponse", balance);
		return "account";
	}

	/**
	 * Method send balance request and store into the database if balance request is
	 * completed.
	 */
	@PostMapping("/pay")
	public String submitPayment(@ModelAttribute BalanceResponse balanceResponse, Model model) {
		PaymentStatus status = accountService.payBalance(balanceResponse);
		model.addAttribute("balanceResponse", new BalanceResponse());
		model.addAttribute("account", new Account());
		model.addAttribute("paymentStatus", status.getMessage());
		return "account";
	}

}
