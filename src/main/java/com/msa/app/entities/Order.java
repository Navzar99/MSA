package com.msa.app.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<MenuProduct> productList;


    public Order() {
    }
}