package com.msa.app.repositories;
import com.msa.app.entities.CustomerTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerTableRepository extends JpaRepository<CustomerTable, Integer> {
    CustomerTable findTopByNumber(Integer id);
    List<CustomerTable> findCustomerTablesByDoesRequestWaiterEquals(Boolean doesRequestWaiter);
}