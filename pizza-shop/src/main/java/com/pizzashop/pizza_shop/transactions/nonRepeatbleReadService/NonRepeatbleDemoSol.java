package com.pizzashop.pizza_shop.transactions.nonRepeatbleReadService;

import com.pizzashop.pizza_shop.entity.Pizza;
import com.pizzashop.pizza_shop.repository.PizzaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class NonRepeatbleDemoSol {
    @Autowired
    private PizzaRepository pizzaRepository;

    @PersistenceContext
    EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(NonRepeatbleDemo.class);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void transactionA() throws Exception
    {
        Pizza pizza = pizzaRepository.findById(1L).orElseThrow();
        logger.info("Transaction A first Read: "+pizza.getPrice());



        Thread.sleep(6000);
        // wait for Transaction B to commit




        entityManager.refresh(pizza);// Clear persistence context so next query hits DB

        Pizza pizza1 = pizzaRepository.findById(1L).orElseThrow();
        logger.info("Transaction A second Read: "+pizza1.getPrice());
    }

    @Transactional
    public void transactionB() throws Exception
    {
        Pizza pizza = pizzaRepository.findById(1L).orElseThrow();
        pizza.setPrice(new BigDecimal(360));
        pizzaRepository.save(pizza);
        pizzaRepository.flush();
        logger.info("Transaction B updated {} pizza price to {}",pizza.getName(),pizza.getPrice());
    }
}
