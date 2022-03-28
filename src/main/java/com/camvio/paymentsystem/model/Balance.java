package com.camvio.paymentsystem.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Balance {

    private boolean success;
    private BigDecimal balance;

}