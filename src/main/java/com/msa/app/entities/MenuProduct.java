//package com.msa.app.entities;
//
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
//import javax.persistence.Entity;
//import javax.persistence.ManyToMany;
//import java.util.Set;
//
//@EqualsAndHashCode(callSuper = true)
//@Entity
//@Data
//public class MenuProduct extends InventoryProduct {
//
//    // id declared in parent
//
//    private Float price;
//
//    @ManyToMany(mappedBy = "productList")
//    private Set<Order> order;
//
//
//    public MenuProduct() {
//    }
//}