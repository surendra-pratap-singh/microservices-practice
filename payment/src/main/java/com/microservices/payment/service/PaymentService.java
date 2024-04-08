package com.microservices.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.payment.entity.Payment;
import com.microservices.payment.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    private final Logger log = LoggerFactory.getLogger(PaymentService.class);

    public String hello() throws JsonProcessingException {
        String str = "payment service default data";
        log.info("PaymentService str : {}",new ObjectMapper().writeValueAsString(str));
        return str;
    }

    public Payment doPayment(Payment payment) throws JsonProcessingException {
        log.info("PaymentService str : {}",new ObjectMapper().writeValueAsString(payment));
        payment.setTransactionId(UUID.randomUUID().toString());
        return paymentRepository.save(payment);
    }
}
