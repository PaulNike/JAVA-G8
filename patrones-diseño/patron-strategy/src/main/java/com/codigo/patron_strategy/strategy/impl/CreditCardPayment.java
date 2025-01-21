package com.codigo.patron_strategy.strategy.impl;

import com.codigo.patron_strategy.strategy.PaymentStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;

    @Override
    public void pay(double amount) {
        log.info("Pagando el monto de: " + amount
        + "Con Tarjeta de credito: "+ cardNumber +
                "Pertenciente a: " + cardHolderName);
    }
}
