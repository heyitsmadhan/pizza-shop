package com.pizzashop.pizza_shop.transactions.propagations;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
public class PaymentService {

    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void makePayment()
    {
        System.out.println("payment started!");

        if(true)
        {
            throw new RuntimeException("payment failed!");
        }

        System.out.println("payment completed!");
    }

}
