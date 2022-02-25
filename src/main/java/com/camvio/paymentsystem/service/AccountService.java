package com.camvio.paymentsystem.service;

import com.camvio.paymentsystem.model.BalanceResponse;
import com.camvio.paymentsystem.model.PaymentStatus;


public interface AccountService {
	
	/**
	 * @param accountNumber
	 * @return
	 */
	BalanceResponse displayBalance(String accountNumber);

	/**
	 * @param balanceResponse
	 * @return
	 */
	PaymentStatus payBalance(BalanceResponse balanceResponse);
}
