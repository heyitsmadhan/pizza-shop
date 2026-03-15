package com.pizzashop.pizza_shop.transactions.basic;

import com.pizzashop.pizza_shop.entity.Pizza;
import com.pizzashop.pizza_shop.entity.Order;
import com.pizzashop.pizza_shop.enums.OrderStatus;
import com.pizzashop.pizza_shop.repository.OrderRepository;
import com.pizzashop.pizza_shop.repository.PizzaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class BasicTransactionDemo {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Transactional
    public void placeOrder(long pizzaId,int quantity)
    {
        Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow();

        if(pizza.getStock()<quantity)
        {
            throw new RuntimeException("not enough stock");
        }

        Order order = new Order();
        order.setPizza(pizza);
        order.setOrderQuantity(quantity);
        order.setStatus(OrderStatus.CREATED);
        order.setTotalPrice(pizza.getPrice().multiply(new BigDecimal(quantity)));
        orderRepository.save(order);

        pizza.setStock(pizza.getStock()-quantity);
        pizzaRepository.save(pizza);

        // simulate payment failure
        throw new RuntimeException("payment failed!");

    }
}
