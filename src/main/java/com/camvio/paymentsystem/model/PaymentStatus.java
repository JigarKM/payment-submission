package com.camvio.paymentsystem.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentStatus {

    private boolean success;
    private int id;
    private String message;

    @Override
    public String toString() {
        return "PaymentStatus [success=" + success + ", id=" + id + ", message=" + message + "]";
    }


}
