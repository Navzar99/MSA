package com.msa.app.services;

import com.msa.app.dtos.CustomerTableDTO;
import com.msa.app.entities.CustomerTable;
import com.msa.app.repositories.CustomerTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public CustomerTable addTableInSequence() {
        CustomerTable newCustomerTable = new CustomerTable(1, false);


        CustomerTable highestNumber = customerTableRepository.findFirstByOrderByNumberDesc();

        if (highestNumber != null){
            newCustomerTable.setNumber(highestNumber.getNumber() + 1);
        }

        return customerTableRepository.save(newCustomerTable);
    }

    public List<CustomerTable> getAllTables() {
        return customerTableRepository.findAll();
    }

    public List<CustomerTable> getRequestedTables(){ return customerTableRepository.findCustomerTablesByDoesRequestWaiterEquals(true); }

    public void deleteTableById(Integer id) {
        customerTableRepository.deleteById(id);
    }

    public void deleteTable() {
        customerTableRepository.delete(customerTableRepository.findFirstByOrderByNumberDesc());
    }

    public Optional<CustomerTable> editTable(CustomerTableDTO customerTableDTO, Integer id) {
        Optional<CustomerTable> customerTable = customerTableRepository.findById(id);
        if (customerTable.isPresent())
        {
            CustomerTable newCustomerTable = customerTable.get();
            newCustomerTable.setDoesRequestWaiter(customerTableDTO.doesRequestWaiter);
            newCustomerTable.setNumber(customerTableDTO.number);

            return Optional.of(customerTableRepository.save(newCustomerTable));
        }

        return Optional.empty();
    }

    public Optional<CustomerTable> setDoesRequireWaiterTrueByNumber(Integer number) {

        Optional<CustomerTable> customerTable = Optional.ofNullable(customerTableRepository.findByNumber(number));

        if (customerTable.isPresent())
        {
            CustomerTable newCustomerTable = customerTable.get();
            newCustomerTable.setDoesRequestWaiter(true);

            return Optional.of(customerTableRepository.save(newCustomerTable));
        }

        return Optional.empty();
    }

    public Optional<CustomerTable> setDoesRequireWaiterFalseByNumber(Integer number) {

        Optional<CustomerTable> customerTable = Optional.ofNullable(customerTableRepository.findByNumber(number));

        if (customerTable.isPresent())
        {
            CustomerTable newCustomerTable = customerTable.get();
            newCustomerTable.setDoesRequestWaiter(false);

            return Optional.of(customerTableRepository.save(newCustomerTable));
        }

        return Optional.empty();
    }
}
