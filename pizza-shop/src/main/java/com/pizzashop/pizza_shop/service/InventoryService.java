package com.pizzashop.pizza_shop.service;

import com.pizzashop.pizza_shop.entity.Pizza;
import com.pizzashop.pizza_shop.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {


    @Autowired
    private PizzaRepository pizzaRepository;

    public void updatePizzaStock(long pizzaId,int quantity)
    {
        Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow();

        pizza.setStock(pizza.getStock()-quantity);
        pizzaRepository.save(pizza);
    }
}
