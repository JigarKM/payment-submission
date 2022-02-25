package com.camvio.paymentsystem.model;

import java.math.BigDecimal;

public class Balance {

	private boolean success;

	private BigDecimal balance;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
