package com.pizzashop.pizza_shop.transactions.supportsDemo;

import com.pizzashop.pizza_shop.repository.PizzaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SupportService {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Transactional(propagation=Propagation.SUPPORTS)
    public void checkStock()
    {
        System.out.println("total available stock: "+pizzaRepository.totalStock());
    }
}
