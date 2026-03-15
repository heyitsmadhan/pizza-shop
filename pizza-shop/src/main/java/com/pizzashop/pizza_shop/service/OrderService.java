package com.pizzashop.pizza_shop.service;

import com.pizzashop.pizza_shop.entity.Order;
import com.pizzashop.pizza_shop.entity.Pizza;
import com.pizzashop.pizza_shop.enums.OrderStatus;
import com.pizzashop.pizza_shop.repository.OrderRepository;
import com.pizzashop.pizza_shop.repository.PizzaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
public class OrderService {


    private OrderRepository orderRepository;

    private PaymentService paymentService;
    private PizzaRepository pizzaRepository;
    private InventoryService inventoryService;
    public OrderService(OrderRepository orderRepository,PaymentService paymentService,
                        PizzaRepository pizzaRepository,InventoryService inventoryService)
    {
        this.orderRepository=orderRepository;
        this.paymentService=paymentService;
        this.pizzaRepository=pizzaRepository;
        this.inventoryService= inventoryService;
    }

    public Order createOrder(Order order)
    {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders()
    {
        return orderRepository.findAll();
    }

    public void cancelOrder(long orderId)
    {
        orderRepository.deleteById(orderId);
    }

//    @Transactional
//    public void placeOrder(long pizzaId, int quantity)
//    {
//        Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow();
//
//        BigDecimal totalPrice = pizza.getPrice() .multiply(new BigDecimal(quantity));
//
//        Order order = new Order();
//        order.setPizza(pizza);
//        order.setTotalPrice(totalPrice);
//        order.setOrderQuantity(quantity);
//        order.setStatus(OrderStatus.CREATED);
//
//        orderRepository.save(order);
//
//        inventoryService.updatePizzaStock(pizzaId,quantity);
//
//       paymentService.processPayment();
//    }
}
