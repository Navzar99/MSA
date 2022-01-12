package com.msa.app.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer number;
    private Boolean doesRequestWaiter = false;

    public Table() {
    }

    public Table(Integer tableNumber) {
        this.number = tableNumber;
    }
}