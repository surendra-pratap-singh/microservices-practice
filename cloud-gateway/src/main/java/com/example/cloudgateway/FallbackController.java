package com.example.cloudgateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@RestController
@RefreshScope
public class FallbackController {

    @Value("${microservice.order-service.endpoints.endpoint.uri}")
    private String ORDER_SERVICE_URL;

    @Autowired
    @Lazy
    RestTemplate restTemplate;

    @RequestMapping("/orderFallback")
    public Mono<String> orderFallback() {
        return Mono.just("Order service is taking too long to respond!");
    }

    @RequestMapping("/paymentFallback")
    public String paymentFallback() {
        String temp = "|||";
        try {
            temp = temp + restTemplate.getForObject(ORDER_SERVICE_URL, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // String template = restTemplate.getForObject("http://ORDER-SERVICE/order/other", String.class);
        return "Payment service is taking too long to respond! + " + temp;
    }
}
