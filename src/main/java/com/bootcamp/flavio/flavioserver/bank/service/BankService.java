
package com.bootcamp.flavio.flavioserver.bank.service;

import com.bootcamp.flavio.flavioserver.bank.Bank;
import com.bootcamp.flavio.flavioserver.bank.repository.BankRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class BankService {
    @Autowired 
    BankRepository bankRepository;
    
     public List<Bank> getAllBanks(){
         return bankRepository.findAll();             
      }
     
     public Bank getBankById(Long id){
          return bankRepository.findById(id).get();              
      }
     
     public boolean existBankById(Long id){
         return bankRepository.existsById(id);
     }
     
     public Bank createAccount(Bank bank) {              
        return bankRepository.save(bank);                 
      }
     
     public void deleteBankById(Long id) {
          bankRepository.deleteById(id);
     }
     
     
}
