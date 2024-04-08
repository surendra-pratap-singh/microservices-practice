package com.microservices.order.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentServiceFeignClient {

    @GetMapping("payment/hello")
    public String hello();
}
