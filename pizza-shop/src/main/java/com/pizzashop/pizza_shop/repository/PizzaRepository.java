package com.pizzashop.pizza_shop.repository;

import com.pizzashop.pizza_shop.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza,Long> {

    @Query("SELECT SUM(stock) FROM Pizza")
    public int totalStock();
}
