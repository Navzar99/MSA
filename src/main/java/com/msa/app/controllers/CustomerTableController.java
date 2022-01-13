package com.msa.app.controllers;

import com.msa.app.dtos.CustomerTableDTO;
import com.msa.app.entities.CustomerTable;
import com.msa.app.services.CustomerTableServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/tables")
public class CustomerTableController {
    private final CustomerTableServices customerTableServices;

    public CustomerTableController(CustomerTableServices tableServices) {
        this.customerTableServices = tableServices;
    }

    // Post
    @PostMapping(path="/addNewTable")
    public CustomerTable addNewTable(@RequestBody CustomerTableDTO tableDTO) {
        return customerTableServices.addTable(tableDTO);
    }

    @PostMapping(path="/addTableInSequence")
    public CustomerTable addTableInSequence() {
        return customerTableServices.addTableInSequence();
    }


    // Get
    @GetMapping(path = "/getAllTables")
    public List<CustomerTable> getTables(){
        return customerTableServices.getAllTables();
    }

    @GetMapping(path = "/getRequestedTables")
    public List<CustomerTable> getTablesWithRequests(){
        return customerTableServices.getRequestedTables();
    }


    // Delete
    @DeleteMapping(path = "/removeTableById/{id}")
    public void removeTableById(@PathVariable Integer id){
        customerTableServices.deleteTableById(id);
    }

    @DeleteMapping(path = "/removeTableFromSequence")
    public void removeTable(){
        customerTableServices.deleteTable();
    }


    // Put
    @PutMapping(path="/editTableById/{id}")
    public CustomerTable editTable(@RequestBody CustomerTableDTO customerTableDTO, @PathVariable("id") Integer id) {
        return customerTableServices.editTable(customerTableDTO, id);
    }

    @PutMapping(path="/setDoesRequireWaiterTrueById/{id}")
    public CustomerTable setDoesRequireWaiterTrue(@PathVariable("id") Integer id) {
        return customerTableServices.setDoesRequireWaiterTrue(id);
    }

    @PutMapping(path="/setDoesRequireWaiterFalseById/{id}")
    public CustomerTable setDoesRequireWaiterFalse(@PathVariable("id") Integer id) {
        return customerTableServices.setDoesRequireWaiterFalse(id);
    }
}
