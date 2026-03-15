package com.pizzashop.pizza_shop.entity;

import com.pizzashop.pizza_shop.enums.PizzaSize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "pizza_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private PizzaSize pizzaSize;
    private BigDecimal price;
    private Boolean available;
    private Integer stock;

//    @OneToMany(mappedBy = "pizza")
//    private List<Order> orders = new ArrayList<>();
}
