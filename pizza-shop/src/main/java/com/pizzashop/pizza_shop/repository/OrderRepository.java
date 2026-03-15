package com.pizzashop.pizza_shop.repository;

import com.pizzashop.pizza_shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {


}
