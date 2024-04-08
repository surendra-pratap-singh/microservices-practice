package com.microservices.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservices.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("hello")
    public String hello() throws JsonProcessingException {
        String s = orderService.saveOrder();
        return "HELLO ORDER SERVICE / "+s;
    }

    @GetMapping("other")
    public String other(){
        return "HELLO ORDER SERVICE 3rd party API!";
    }

}
