package com.msa.app.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// schema for a DB table
@Entity // This tells Hibernate to make a table out of this class
@Data
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userId;
    private String shopName;
    private String name;
    private String password;
    private Boolean isAdmin;

    public User() {
    }

    public User(String shopName, String name, String password, Boolean isAdmin) {
        this.shopName = shopName;
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}