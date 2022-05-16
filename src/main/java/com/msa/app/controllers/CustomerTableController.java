package com.msa.app.controllers;

import com.msa.app.dtos.CustomerTableDTO;
import com.msa.app.entities.CustomerTable;
import com.msa.app.services.CustomerTableServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/tables")
@CrossOrigin("http://localhost:4200")
public class CustomerTableController {
    private final CustomerTableServices customerTableServices;

    public CustomerTableController(CustomerTableServices tableServices) {
        this.customerTableServices = tableServices;
    }

    // POST
    @PostMapping(path="/addNewTable")
    public ResponseEntity<CustomerTable> addNewTable(@RequestBody CustomerTableDTO tableDTO) {
        return new ResponseEntity<CustomerTable>(customerTableServices.addTable(tableDTO), HttpStatus.OK);
    }

    @PostMapping(path="/addTableInSequence")
    public ResponseEntity<CustomerTable> addTableInSequence() {
        return new ResponseEntity<CustomerTable>(customerTableServices.addTableInSequence(), HttpStatus.OK);
    }


    // GET
    @GetMapping(path = "/getAllTables")
    public ResponseEntity<List<CustomerTable>> getTables(){
        return new ResponseEntity<List<CustomerTable>>(customerTableServices.getAllTables(), HttpStatus.OK);
    }

    @GetMapping(path = "/getRequestedTables")
    public ResponseEntity<List<CustomerTable>> getTablesWithRequests(){
        return new ResponseEntity<List<CustomerTable>>(customerTableServices.getRequestedTables(), HttpStatus.OK);
    }


    // DELETE
    @DeleteMapping(path = "/removeTableById/{id}")
    public void removeTableById(@PathVariable Integer id) {
        customerTableServices.deleteTableById(id);
    }

    @DeleteMapping(path = "/removeTableFromSequence")
    public void removeTable() {
        customerTableServices.deleteTable();
    }


    // PUT
    @PutMapping(path="/editTableById/{id}")
    public ResponseEntity<CustomerTable> editTable(@RequestBody CustomerTableDTO customerTableDTO, @PathVariable("id") Integer id) {
        Optional<CustomerTable> searchedTable = customerTableServices.editTable(customerTableDTO, id);
        if (searchedTable.isPresent())
            return new ResponseEntity<CustomerTable>(searchedTable.get(), HttpStatus.OK);

        return new ResponseEntity<CustomerTable>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(path="/setDoesRequireWaiterTrueByNumber/{id}")
    public ResponseEntity<CustomerTable> setDoesRequireWaiterTrue(@PathVariable("id") Integer number) {
        Optional<CustomerTable> searchedTable = customerTableServices.setDoesRequireWaiterTrueByNumber(number);
        if (searchedTable.isPresent())
            return new ResponseEntity<CustomerTable>(searchedTable.get(), HttpStatus.OK);

        return new ResponseEntity<CustomerTable>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(path="/setDoesRequireWaiterFalseByNumber/{id}")
    public ResponseEntity<CustomerTable> setDoesRequireWaiterFalse(@PathVariable("id") Integer number) {

        Optional<CustomerTable> searchedTable = customerTableServices.setDoesRequireWaiterFalseByNumber(number);
        if (searchedTable.isPresent())
            return new ResponseEntity<CustomerTable>(searchedTable.get(), HttpStatus.OK);

        return new ResponseEntity<CustomerTable>(HttpStatus.NOT_FOUND);
    }
}
