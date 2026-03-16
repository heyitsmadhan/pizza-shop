package com.pizzashop.pizza_shop.transactions.rollback;

import com.pizzashop.pizza_shop.entity.Pizza;
import com.pizzashop.pizza_shop.enums.OrderStatus;
import com.pizzashop.pizza_shop.repository.OrderRepository;
import com.pizzashop.pizza_shop.repository.PizzaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzashop.pizza_shop.entity.Order;

import java.math.BigDecimal;

@Service
public class RollBackRulesDemo {

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private PizzaRepository pizzaRepository;


    @Transactional
    public void runTimeExceptionDemo()
    {
        Order order = new Order();
        order.setOrderQuantity(1);
        order.setStatus(OrderStatus.CREATED);
        order.setTotalPrice(new BigDecimal("250"));
        orderRepository.save(order);

        Pizza pizza = pizzaRepository.findById(1L).orElseThrow();
        pizza.setStock(pizza.getStock()-1);

        //simulate runtime exception
        throw new RuntimeException("Runtime Exception - payment failed!");
    }

    @Transactional(rollbackFor=Exception.class)
    public void checkedException() throws Exception
    {
        Order order = new Order();
        order.setOrderQuantity(2);
        order.setStatus(OrderStatus.CREATED);
        order.setTotalPrice(new BigDecimal("600"));
        orderRepository.save(order);

        Pizza pizza = pizzaRepository.findById(2L).orElseThrow();
        pizza.setStock(pizza.getStock()-2);

        //simulate runtime exception
        throw new Exception("Checked Exception- payment failed!");
    }
}
