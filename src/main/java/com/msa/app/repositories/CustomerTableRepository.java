package com.msa.app.repositories;
import com.msa.app.entities.CustomerTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerTableRepository extends JpaRepository<CustomerTable, Integer> {
    CustomerTable findFirstByOrderByNumberDesc();
    List<CustomerTable> findCustomerTablesByDoesRequestWaiterEquals(Boolean doesRequestWaiter);
}