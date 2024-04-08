package com.microservices.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.order.feignclient.PaymentServiceFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RefreshScope
public class OrderService {

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String PAYMENT_URL;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Autowired
    private PaymentServiceFeignClient paymentServiceFeignClient;

    private final Logger log = LoggerFactory.getLogger(OrderService.class);


    public String saveOrder() throws JsonProcessingException {

        String str = "default data : ";
        log.info("OrderService str : {}",new ObjectMapper().writeValueAsString(str));
        try {
            String a = paymentServiceFeignClient.hello();
            System.out.println("paymentServiceFeignClient.getData(): "+a);
            str = str+ restTemplate.getForObject(PAYMENT_URL, String.class);
            log.info("OrderService str Payment service response : {}",new ObjectMapper().writeValueAsString(str));
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }
}
