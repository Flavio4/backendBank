
package com.bootcamp.flavio.flavioserver.account.controller;


import com.bootcamp.flavio.flavioserver.account.Account;
import com.bootcamp.flavio.flavioserver.account.service.AccountService;
import com.bootcamp.flavio.flavioserver.customers.Customer;
import com.bootcamp.flavio.flavioserver.customers.service.CustomerService;
import com.bootcamp.flavio.flavioserver.protocol.EndpointFailure;
import com.bootcamp.flavio.flavioserver.protocol.EndpointRequest;
import com.bootcamp.flavio.flavioserver.protocol.EndpointResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    
    
    @Autowired 
    AccountService accountService;
    
    @Autowired
    CustomerService customerService;
    
    
    @PostMapping("/all")   //En un banco no deberia existir.                   
    public EndpointResponse getAllAccount(){
        var response = new EndpointResponse<List<Account>>();
        response.setId("0");
        response.setMessage(accountService.getAllAccount());
        return response;
    }
    
    @PostMapping("/getbyid")
    public EndpointResponse  getAccountById(@RequestBody EndpointRequest<Account> request){
        var response = new EndpointResponse<>();
        var failure = new EndpointFailure<String>();
        if(!(request.getMessage().getId() == null)){
            boolean exist = accountService.existAccountById(request.getMessage().getId());
            if (exist) {
                response.setId("0");
                response.setMessage(accountService.getAccountById(request.getMessage().getId()));
            }else{
                response.setId("1");
                response.setMessage(failure.getFailureMessage("2"));
            }
            
        }else{
            
            response.setId("1");
            response.setMessage(failure.getFailureMessage("1"));
        }
        
        
        return response;
    }
    
    @PostMapping("/delete")
    public EndpointResponse deleteAccountById(@RequestBody EndpointRequest<Account> request){
       var response = new EndpointResponse<>();
       var failure = new EndpointFailure<String>();
       
       if(!(request.getMessage().getId() == null)){
           boolean existAccount = accountService.existAccountById(request.getMessage().getId());
            if(existAccount){
                response.setId("0");
                response.setMessage(request.getMessage().getId());
                accountService.deleteAccountById(request.getMessage().getId());
            
            }else{
                response.setId("1");
                response.setMessage(failure.getFailureMessage("2"));
            }
       }else{
            response.setId("1");
            response.setMessage(failure.getFailureMessage("1"));
       }
       
      
       return response;
    }
    
   @PostMapping("/create")
   public EndpointResponse createAccount(@RequestBody EndpointRequest<Account> request){
       var response = new EndpointResponse<>();  
       var failure = new EndpointFailure<String>();
       if(!(request.getMessage().getCustomer().getId() == null)){
           boolean customerExist = customerService.existCustomerById(request.getMessage().getCustomer().getId());
           if(customerExist){
               Account newAccount = accountService.createAccount(request.getMessage());    
               response.setId("0");
               response.setMessage(newAccount.getId());
           }else{
               response.setId("1");
               response.setMessage(failure.getFailureMessage("6"));
           }

       }else{
           response.setId("1");
           response.setMessage(failure.getFailureMessage("1"));
       }

       return response;
    }
   
    
   @PostMapping("/getbycustomer")
     public EndpointResponse getBycustomer(@RequestBody EndpointRequest<Customer> request){
         var response = new EndpointResponse<>();
         var failure = new EndpointFailure<String>();
         if(!(request.getMessage().getId() == null)){
            boolean customerExist = customerService.existCustomerById(request.getMessage().getId());
            if(customerExist){
                var accountList = accountService.getAccountsByCustomer(request.getMessage());
            
                response.setId("0");
                response.setMessage(accountList);

            }else{
                response.setId("1");
                response.setMessage(failure.getFailureMessage("6"));
            
            }
           
         }else{
             
           response.setId("1");
           response.setMessage(failure.getFailureMessage("1"));
         }
            
             
         return response;
     }
   
   @PostMapping("/update")
    public EndpointResponse updateAccount(@RequestBody EndpointRequest<Account> request){    
        var response = new EndpointResponse<>();
        var failure = new EndpointFailure<String>();
        try {
            var existAccount = accountService.existAccountById(request.getMessage().getId());
            if (existAccount) {
                
                var newUpdateAccount = accountService.updateAccount(request.getMessage(), request.getMessage().getId());
                response.setId("0");
                response.setMessage(newUpdateAccount.getId()); 
            } else {
                
                response.setId("1");
                response.setMessage(failure.getFailureMessage("2"));  
                
            }
            
            
        } catch (Throwable e) {
            
            response.setId("1");
            response.setMessage(failure.getFailureMessage("1"));        
        }
        
        return response;
    }
   
}
    
    
   
    
   
