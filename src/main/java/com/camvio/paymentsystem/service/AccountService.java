package com.camvio.paymentsystem.service;

import com.camvio.paymentsystem.model.BalanceResponse;
import com.camvio.paymentsystem.model.PaymentStatus;


public interface AccountService {

	BalanceResponse displayBalance(String accountNumber);

	PaymentStatus payBalance(BalanceResponse balanceResponse);
}
