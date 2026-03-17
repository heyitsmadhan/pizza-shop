package com.pizzashop.pizza_shop.transactions.supportsDemo;

import com.pizzashop.pizza_shop.entity.Order;
import com.pizzashop.pizza_shop.entity.Pizza;
import com.pizzashop.pizza_shop.enums.OrderStatus;
import com.pizzashop.pizza_shop.repository.OrderRepository;
import com.pizzashop.pizza_shop.repository.PizzaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WithTransaction {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private SupportService service;

    @Transactional
    public void crateOrder() throws Exception
    {
        Order order = new Order();
        order.setOrderQuantity(1);
        order.setStatus(OrderStatus.CREATED);
        order.setTotalPrice(new BigDecimal(200));

        Pizza pizza = pizzaRepository.findById(3L).orElseThrow();
        order.setPizza(pizza);
        try{
            throw new RuntimeException("runtime exception");
        }
        catch (Exception e)
        {
            System.out.println("exception caught!");
        }
        service.checkStock();

        System.out.println("order is getting saved");
        orderRepository.save(order);
        pizza.setStock(pizza.getStock()-1);
        System.out.println("pizza is getting saved");
        pizzaRepository.save(pizza);

    }
}
