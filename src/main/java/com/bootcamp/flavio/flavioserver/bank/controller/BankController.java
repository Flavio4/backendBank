
package com.bootcamp.flavio.flavioserver.bank.controller;

import com.bootcamp.flavio.flavioserver.bank.Bank;
import com.bootcamp.flavio.flavioserver.bank.service.BankService;
import com.bootcamp.flavio.flavioserver.protocol.EndpointRequest;
import com.bootcamp.flavio.flavioserver.protocol.EndpointResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankController {
    
    @Autowired 
    BankService bankService;
    
    
    @PostMapping("/all")
    public EndpointResponse getAllBank(){
        var response = new EndpointResponse<List<Bank>>();
        response.setId("0");
        response.setMessage(bankService.getAllBanks());
        return response;    
    }
    
    @PostMapping("/{id}")
    public EndpointResponse getBankById(@PathVariable("id") Long idBank){
        var response = new EndpointResponse<>();
        try {
            response.setId("0");
            response.setMessage(bankService.getBankById(idBank));
        } catch (Exception e) {
            response.setId("1");
            response.setMessage("Ese Banco no existe");
        }
        
      return response;    
    }
    
    
    @PostMapping("/post")
    public EndpointResponse createBank(@RequestBody EndpointRequest<Bank> request){
       var response = new EndpointResponse<>();
       try {
            Bank newBank = bankService.createAccount(request.getMessage());    
            response.setId("0");
            response.setMessage(newBank);
        
    
       }catch (Exception e) {  
            response.setId("1"); 
            response.setMessage(e.getCause().getCause().getMessage());
       }
       return response;
    }
    
    
     @PostMapping("/delete/{id}")
    public EndpointResponse deleteBankById(@PathVariable("id") long id){
        var response = new EndpointResponse<>();
        try {
            bankService.deleteBankById(id);
            response.setId("0");
            response.setMessage(id);
                
        } catch (Exception e) {
                        
            response.setId("1");
            response.setMessage("Error, el elemento no existe o ya se elimino");

        }
        return response;
    }
    
}
