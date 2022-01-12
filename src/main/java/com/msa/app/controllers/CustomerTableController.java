package com.msa.app.controllers;

import com.msa.app.dtos.CustomerTableDTO;
import com.msa.app.entities.CustomerTable;
import com.msa.app.services.CustomerTableServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/tables")
public class CustomerTableController {
    private final CustomerTableServices tableServices;

    public CustomerTableController(CustomerTableServices tableServices) {
        this.tableServices = tableServices;
    }

    @PostMapping(path="/addNewTable")
    public CustomerTable addNewTable(@RequestBody CustomerTableDTO tableDTO) {
        return tableServices.addTable(tableDTO);
    }


    @GetMapping(path = "/getTables")
    public List<CustomerTable> getTables(){
        return tableServices.getAllTables();
    }

    @GetMapping(path = "/getRequestedTables")
    public List<CustomerTable> getTablesWithRequests(){
        return tableServices.getRequestedTables();
    }



    @DeleteMapping(path = "/removeTableById/{id}") // pathvariable
    public void removeTableById(@PathVariable Integer id){
        tableServices.deleteTableById(id);
    }

    @DeleteMapping(path = "/removeTable") // pathvariable
    public void removeTable(){
        tableServices.deleteTable();
    }
}
