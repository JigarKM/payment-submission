package com.camvio.paymentsystem.model;

public class PaymentStatus {

	private boolean success;
	private int id;
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "PaymentStatus [success=" + success + ", id=" + id + ", message=" + message + "]";
	}
	
	
	

}
