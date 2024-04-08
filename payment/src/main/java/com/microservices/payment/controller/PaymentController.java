package com.microservices.payment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservices.payment.entity.Payment;
import com.microservices.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/hello")
    public String hello() throws JsonProcessingException {
        String s = paymentService.hello();
        return "HELLO PAYMENT SERVICE || "+s;
    }

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
        return paymentService.doPayment(payment);
    }
}
