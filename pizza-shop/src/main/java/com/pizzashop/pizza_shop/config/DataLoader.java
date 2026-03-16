package com.pizzashop.pizza_shop.config;

import com.pizzashop.pizza_shop.entity.Pizza;
import com.pizzashop.pizza_shop.enums.PizzaSize;
import com.pizzashop.pizza_shop.repository.OrderRepository;
import com.pizzashop.pizza_shop.repository.PizzaRepository;
import com.pizzashop.pizza_shop.service.OrderService;
import com.pizzashop.pizza_shop.transactions.basic.BasicTransactionDemo;
import com.pizzashop.pizza_shop.transactions.rollback.RollBackRulesDemo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

//    @Bean
//    CommandLineRunner loadData(PizzaRepository pizzaRepository)
//    {
//        return  new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//
//
//            }
//        };
//    }


//    @Bean
//    CommandLineRunner loadData(PizzaRepository pizzaRepository)
//    {
//        return args ->{
//            {
//
//                Pizza pizza1 = new Pizza();
//                pizza1.setName("Margherita");
//                pizza1.setPizzaSize(PizzaSize.MEDIUM);
//                pizza1.setPrice(new BigDecimal("250"));
//                pizza1.setAvailable(true);
//                pizza1.setStock(20);
//
//                Pizza pizza2 = new Pizza();
//                pizza2.setName("Pepperoni");
//                pizza2.setPizzaSize(PizzaSize.LARGE);
//                pizza2.setPrice(new BigDecimal("300"));
//                pizza2.setAvailable(true);
//                pizza2.setStock(15);
//
//                Pizza pizza3 = new Pizza();
//                pizza3.setName("Veggie");
//                pizza3.setPizzaSize(PizzaSize.SMALL);
//                pizza3.setPrice(new BigDecimal("200"));
//                pizza3.setAvailable(true);
//                pizza3.setStock(10);
//
//                pizzaRepository.save(pizza1);
//                pizzaRepository.save(pizza2);
//                pizzaRepository.save(pizza3);
//
//            }
//        };
//
//    }


//    @Bean
//    CommandLineRunner testOrder(OrderService orderService)
//    {
//        return args -> {
//
//            orderService.placeOrder(3,1);
//        };
//    }

//    @Bean
//    CommandLineRunner testBasicTransactionDemo(BasicTransactionDemo basicTransactionDemo)
//    {
//        return args -> {
//
//           try
//           {
//               basicTransactionDemo.placeOrder(1L,1);
//           }
//           catch (Exception e)
//           {
//               System.out.println("Transaction Rolled back due to : "+e.getMessage());
//           }
//        };
//
//    }


    @Bean
    CommandLineRunner testRollBackRules(RollBackRulesDemo rollBackRulesDemo)
    {
        return args ->{
            try
            {
                rollBackRulesDemo.runTimeExceptionDemo();
            }
            catch (Exception e)
            {
                System.out.println("roll backed due to "+e.getMessage());
            }
            try
            {
                rollBackRulesDemo.checkedException();
            }
            catch (Exception e)
            {
                System.out.println("roll backed due to "+e.getMessage());
            }
        };
    }
}
