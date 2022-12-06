
package com.bootcamp.flavio.flavioserver.account.service;


import com.bootcamp.flavio.flavioserver.account.Account;
import com.bootcamp.flavio.flavioserver.account.repository.AccountRepository;
import com.bootcamp.flavio.flavioserver.customers.Customer;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    
    
    @Autowired AccountRepository accountRepository;
    
      public List<Account> getAllAccount(){
         return accountRepository.findAll();             
      }
      
      public Account getAccountById(Long id){
          return accountRepository.findById(id).get();              
      }

      public void deleteAccountById(Long id) {
          accountRepository.deleteById(id);
       }
      
      public boolean existAccountById(Long id){
            return accountRepository.existsById(id);
      }

      
      public Account createAccount(Account account) {              
        return accountRepository.save(account);                 
      }

     public Account updateAccount(Account account, Long id) {
          Account accountDb = accountRepository.findById(id).get();
          
          if (Objects.nonNull(account.getAccountType()) && !"".equalsIgnoreCase(account.getAccountType())) {
              
                accountDb.setAccountType(account.getAccountType());
            
          }
          return accountRepository.save(accountDb);
     }
     
      
      public void updateBalanceTargetAccount(Account account, BigDecimal ammount) {               
         BigDecimal newAmmount = account.getBalance().add(ammount);
         account.setBalance(newAmmount);                                         
      }
  
      
      public void updateBalanceSourceAccount(Account account, BigDecimal ammount) {               
         BigDecimal newAmmount = account.getBalance().subtract(ammount);
         account.setBalance(newAmmount);                                            
      }
      
       public List<Account> getAccountsByCustomer(Customer customer){
        return accountRepository.findAllByCustomer(customer);
    }
}