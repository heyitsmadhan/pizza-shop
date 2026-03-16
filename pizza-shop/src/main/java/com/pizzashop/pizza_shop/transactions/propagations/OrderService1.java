package com.pizzashop.pizza_shop.transactions.propagations;

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
public class OrderService1 {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PaymentService paymentService;

    @Transactional
    public void createOrder() throws Exception
    {

        System.out.println("order transaction started!");
        Order order = new Order();

        order.setOrderQuantity(1);
        order.setStatus(OrderStatus.CREATED);
        order.setTotalPrice(new BigDecimal("200"));

        try{
            paymentService.makePayment();
        } catch (Exception e) {
            System.out.println("payment failed but order will continues!");
        }


        Pizza pizza = pizzaRepository.findById(3L).orElseThrow();
        pizza.setStock(pizza.getStock()-1);
        pizzaRepository.save(pizza);
        order.setPizza(pizza);
        orderRepository.save(order);
        System.out.println("pizza updated successfully!");
        System.out.println("Order saved successfully!");
    }
}
