
package com.bootcamp.flavio.flavioserver.customers.repository;

import com.bootcamp.flavio.flavioserver.customers.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    
}
