package com.pizzashop.pizza_shop.service;

import com.pizzashop.pizza_shop.entity.Pizza;
import com.pizzashop.pizza_shop.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private PizzaRepository pizzaRepository;
    public PizzaService(PizzaRepository pizzaRepository)
    {
        this.pizzaRepository=pizzaRepository;
    }
    public Pizza createPizza(Pizza pizza)
    {
        return pizzaRepository.save(pizza);
    }
    public List<Pizza> getAllPizzas()
    {
        return pizzaRepository.findAll();
    }
//    public void updateStock(long pizzaId,int quantity)
//    {
//        Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow();
//        pizza.setStock(pizza.getStock()-quantity);
//        pizzaRepository.save(pizza);
//    }
    public void deletePizza(long pizzaId)
    {
        pizzaRepository.deleteById(pizzaId);
    }

}
