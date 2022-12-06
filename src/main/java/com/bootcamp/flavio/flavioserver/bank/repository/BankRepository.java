
package com.bootcamp.flavio.flavioserver.bank.repository;

import com.bootcamp.flavio.flavioserver.bank.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BankRepository extends JpaRepository<Bank, Long>{
    
}
