package com.msa.app.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;



@Entity
@Data
public class MenuProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuProductId;

    private String name;
//    private String allergens;
//    private String calories;
//    private Boolean isVegetarian
    private String description;
    private Boolean isInStock;
    private Float price;

//    @ManyToMany(mappedBy = "productList")
//    private Set<Order> order;

    private Float roundTwoDecimals(Float d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Float.parseFloat(twoDForm.format(d));
    }

    public MenuProduct() {
    }

    public MenuProduct(String name, Float price, String description, Boolean isInStock) {
        this.name = name;
        this.isInStock = isInStock;
        this.price = roundTwoDecimals(price);
        this.description = description;
    }
}