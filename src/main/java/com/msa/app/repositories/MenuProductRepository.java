package com.msa.app.repositories;
import com.msa.app.entities.CustomerTable;
import com.msa.app.entities.MenuProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuProductRepository extends JpaRepository<MenuProduct, Integer> {
    MenuProduct findByName(String Name);
//    List<CustomerTable> findCustomerTablesByDoesRequestWaiterEquals(Boolean doesRequestWaiter);
    List<MenuProduct> findMenuProductsByIsInStockTrue();

}