package com.pizzashop.pizza_shop.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {



    public void processPayment()
    {
        throw new RuntimeException("payment failed!");
    }
}
