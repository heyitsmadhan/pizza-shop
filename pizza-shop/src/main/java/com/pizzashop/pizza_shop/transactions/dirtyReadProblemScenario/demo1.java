package com.pizzashop.pizza_shop.transactions.dirtyReadProblemScenario;


import com.pizzashop.pizza_shop.entity.Pizza;
import com.pizzashop.pizza_shop.repository.PizzaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class demo1 {

    @Autowired
    private PizzaRepository pizzaRepository;

    private static Logger logger = LoggerFactory.getLogger(demo1.class);

    @Transactional(isolation= Isolation.READ_UNCOMMITTED)
    public void transactionA() throws Exception {
        logger.info("transaction A reading the total pizzas available quantity: {} ", pizzaRepository.totalStock());
        Pizza pizza = pizzaRepository.findById(3L).orElseThrow();
        logger.info("Pizza Name: {} and stock: {} ", pizza.getName(), pizza.getStock());
        pizza.setStock(28);
        pizzaRepository.save(pizza); //we are saving pizza table but commit is not going to happen here as
        // at the method end commit will get executed and if no exception is thrown
        logger.info("Pizza Name: {} and updated stock: {} ", pizza.getName(), pizza.getStock());
        logger.info("Transaction A updated stock to {} but NOT committed",pizza.getStock());
        pizzaRepository.flush(); // force hibernate to push the changes immedialty to DB


        Thread.sleep(10000);// this keeps transaction open for 10 seconds
        throw new RuntimeException("An exception occurred in transaction A roll back!");
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void transactionB()
    {
        logger.info("transaction B reading the total pizzas available quantity: {} ", pizzaRepository.totalStock());
        Pizza pizza = pizzaRepository.findById(3L).orElseThrow();
        logger.info("Transaction B: Pizza Name: {} and stock: {} ", pizza.getName(), pizza.getStock());
    }
}
