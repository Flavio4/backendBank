
package com.bootcamp.flavio.flavioserver.exchanges.repository;


import com.bootcamp.flavio.flavioserver.account.Account;
import com.bootcamp.flavio.flavioserver.exchanges.Exchange;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    List<Exchange> findAllByAccount(Account account);
}

