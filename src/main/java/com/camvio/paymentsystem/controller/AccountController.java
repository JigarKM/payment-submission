package com.camvio.paymentsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.camvio.paymentsystem.model.Account;
import com.camvio.paymentsystem.model.BalanceResponse;
import com.camvio.paymentsystem.model.PaymentRequest;
import com.camvio.paymentsystem.model.PaymentStatus;
import com.camvio.paymentsystem.service.AccountService;

/**
 * Controller clas which handle all the operations.
 *
 */
@Controller
public class AccountController {

	@Autowired
	AccountService accountService;

	/**
	 * Method to home page
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/account")
	public String home(Model model) {
		model.addAttribute("account", new Account());
		model.addAttribute("balanceResponse", new BalanceResponse());
		return "account";
	}

	/**
	 * 
	 * Method to display Account details like balance
	 * 
	 * @param account
	 * @param model
	 * @return
	 */
	@PostMapping("/details")
	public String AccountDetails(@ModelAttribute Account account, Model model) {
		System.out.println("ACCOUNT NO IS ::: " + account.getAccountNo());
		BalanceResponse balance = accountService.displayBalance(account.getAccountNo());
		// model.addAttribute("balanceResponse", new BalanceResponse());
		model.addAttribute("balanceResponse", balance);
		return "account";
	}

	/**
	 * 
	 * 
	 * Method send balance request and store into the database if balance request is
	 * completed.
	 * 
	 * @param balanceResponse
	 * @param model
	 * @return
	 */
	@PostMapping("/pay")
	public String payPayment(@ModelAttribute BalanceResponse balanceResponse, Model model) {
		PaymentStatus status = accountService.payBalance(balanceResponse);
		BalanceResponse balanceResponse2 = null;
		model.addAttribute("balanceResponse", new BalanceResponse());
		model.addAttribute("account", new Account());
		model.addAttribute("paymentStatus", status.getMessage());
		return "account";
	}

}
