package com.msa.app.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class CustomerTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer customerTableId;
    private Integer number;
    private Boolean doesRequestWaiter = false;

    public CustomerTable() {
    }

    public CustomerTable(Integer tableNumber) {
        this.number = tableNumber;
        this.doesRequestWaiter = false;
    }

    public CustomerTable(Integer tableNumber, Boolean doesRequestWaiter) {
        this.number = tableNumber;
        this.doesRequestWaiter = doesRequestWaiter;
    }
}