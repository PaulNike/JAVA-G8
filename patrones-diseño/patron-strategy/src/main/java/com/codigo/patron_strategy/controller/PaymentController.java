package com.codigo.patron_strategy.controller;

import com.codigo.patron_strategy.service.PaymentService;
import com.codigo.patron_strategy.strategy.impl.CreditCardPayment;
import com.codigo.patron_strategy.strategy.impl.CryptoPayment;
import com.codigo.patron_strategy.strategy.impl.PayPalPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/strategy/")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/credit-card")
    public String payWithCreditCard(@RequestParam double amount,
                                    @RequestParam String cardNumber,
                                    @RequestParam String cardHolderName){
        paymentService.setPaymentStrategy(new CreditCardPayment(cardNumber,cardHolderName));
        paymentService.processPayment(amount);
        return "Pago Procesado con Tarjeta de Credito";
    }

    @PostMapping("/paypal")
    public String payWithPaypal(@RequestParam double amount,
                                    @RequestParam String email){
        paymentService.setPaymentStrategy(new PayPalPayment(email));
        paymentService.processPayment(amount);
        return "Pago Procesado con PayPal";
    }

    @PostMapping("/crypto")
    public String payWithPCrypto(@RequestParam double amount,
                                @RequestParam String wallet){
        paymentService.setPaymentStrategy(new CryptoPayment(wallet));
        paymentService.processPayment(amount);
        return "Pago Procesado con Crypto";
    }
}
