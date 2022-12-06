
package com.bootcamp.flavio.flavioserver.exchanges.service;


import com.bootcamp.flavio.flavioserver.account.Account;
import com.bootcamp.flavio.flavioserver.bank.myBank.MyBank;
import com.bootcamp.flavio.flavioserver.exchanges.Exchange;
import com.bootcamp.flavio.flavioserver.exchanges.repository.ExchangeRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {
    
    @Autowired
    ExchangeRepository exchangesRepository;
    
    public Exchange saveExchange(Exchange exchange){
        return exchangesRepository.save(exchange);
    }
    
    public List<Exchange> getExchangesByAccount(Account account){
        return exchangesRepository.findAllByAccount(account);
    }
    
     public Exchange findByIdExchange(Long id){
        return exchangesRepository.findById(id).get();
    }
    
    
    public Exchange saveTargetExchange(Account targetAccount, Exchange exchange, String transactionType){
        Exchange newExchange = new Exchange();
        newExchange.setAmmount(exchange.getAmmount());
        newExchange.setCurrency("PYG");
        newExchange.setTransactionType(transactionType);
        newExchange.setAccount(targetAccount);
         if(transactionType.equals("DEPOSIT") || transactionType.equals("WITHDRAW")){
            newExchange.setIdTarget(exchange.getIdTarget());
            newExchange.setIdSource(exchange.getIdTarget());
            
            newExchange.setBankIdTarget(MyBank.getMyBankId());
            newExchange.setBankIdSource(MyBank.getMyBankId());
        }else{
            newExchange.setIdTarget(exchange.getIdTarget());
            newExchange.setIdSource(exchange.getIdSource());
            
            newExchange.setBankIdTarget(MyBank.getMyBankId());
            newExchange.setBankIdSource(MyBank.getMyBankId());
            if(transactionType.equals("EX-TRANSFER")){
                newExchange.setBankIdTarget(exchange.getBankIdTarget());
                newExchange.setBankIdSource(exchange.getBankIdSource());
            }
        }
        
  
        LocalDateTime ldt = LocalDateTime.now();
        newExchange.setTransferDate(DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(ldt));
        return saveExchange(newExchange);
    }
    
    public Exchange saveSourceExchange(Account sourceAccount, Exchange exchange, String transactionType){
        Exchange newExchange = new Exchange();
        newExchange.setAmmount(exchange.getAmmount().negate());
        newExchange.setCurrency("PYG");
        newExchange.setTransactionType(transactionType);
        newExchange.setAccount(sourceAccount);
        if(transactionType.equals("DEPOSIT") || transactionType.equals("WITHDRAW")){
            newExchange.setIdTarget(exchange.getIdSource());
            newExchange.setIdSource(exchange.getIdSource());
            
            newExchange.setBankIdTarget(MyBank.getMyBankId());
            newExchange.setBankIdSource(MyBank.getMyBankId());
        }else{
            newExchange.setIdTarget(exchange.getIdTarget());
            newExchange.setIdSource(exchange.getIdSource());
            
            newExchange.setBankIdTarget(MyBank.getMyBankId());
            newExchange.setBankIdSource(MyBank.getMyBankId());
            if(transactionType.equals("EX-TRANSFER")){
                newExchange.setBankIdTarget(exchange.getBankIdTarget());
                newExchange.setBankIdSource(exchange.getBankIdSource());
            }
        }
        
        
        LocalDateTime ldt = LocalDateTime.now();
        newExchange.setTransferDate(DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(ldt));
        return saveExchange(newExchange);
    }
}
