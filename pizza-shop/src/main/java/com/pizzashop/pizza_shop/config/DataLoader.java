package com.pizzashop.pizza_shop.config;

import com.pizzashop.pizza_shop.transactions.dirtyReadProblemScenario.demo1;
import com.pizzashop.pizza_shop.transactions.propagations.*;
import com.pizzashop.pizza_shop.transactions.supportsDemo.WithTransaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
//    CommandLineRunner testOrder(OrderService1 orderService)
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


//    @Bean
//    CommandLineRunner testRollBackRules(RollBackRulesDemo rollBackRulesDemo)
//    {
//        return args ->{
//            try
//            {
//                rollBackRulesDemo.runTimeExceptionDemo();
//            }
//            catch (Exception e)
//            {
//                System.out.println("roll backed due to "+e.getMessage());
//            }
//            try
//            {
//                rollBackRulesDemo.checkedException();
//            }
//            catch (Exception e)
//            {
//                System.out.println("roll backed due to "+e.getMessage());
//            }
//        };
//    }

//    @Bean
//    CommandLineRunner testPropaqationRequiresNew(OrderService1 orderService1)
//    {
//        return args->{
//          try{
//              orderService1.createOrder();
//          } catch (Exception e) {
//              throw new RuntimeException(e);
//          }
//        };
//    }

//    @Bean
//    CommandLineRunner testSupportsWithTransactional(WithTransaction withTransaction)
//    {
//        return args -> {
//
//            withTransaction.crateOrder();
//        };
//    }

    @Bean
    CommandLineRunner testDirtyReadProblem(demo1 demo1)
    {
        return args -> {

            Thread t1 = new Thread(()->{

                try{
                    demo1.transactionA();
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
            );

            Thread t2 = new Thread(
                    ()->{

                        try
                        {
                            Thread.sleep(2000);
                            demo1.transactionB();
                        }
                        catch (Exception e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
            );

            t1.start();
            t2.start();
        };


    }

}
