package com.msa.app.services;

import com.msa.app.dtos.CustomerTableDTO;
import com.msa.app.entities.CustomerTable;
import com.msa.app.repositories.CustomerTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// actions for users
@Service
public class CustomerTableServices {
    private final CustomerTableRepository customerTableRepository;

    public CustomerTableServices(CustomerTableRepository tableRepository) {
        this.customerTableRepository = tableRepository;
    }

    public CustomerTable addTable(CustomerTableDTO tableDTO) {
        return customerTableRepository.save(tableDTO.mapToTable());
    }

    public List<CustomerTable> getAllTables() {
        return customerTableRepository.findAll();
    }

    public List<CustomerTable> getRequestedTables(){ return customerTableRepository.findCustomerTablesByDoesRequestWaiterEquals(true); }

    public void deleteTableById(Integer id) {
        customerTableRepository.deleteById(id);
    }

    public void deleteTable() {
        customerTableRepository.delete(customerTableRepository.findTopByNumber(1));
    }
}
