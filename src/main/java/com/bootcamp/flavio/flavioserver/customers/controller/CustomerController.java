
package com.bootcamp.flavio.flavioserver.customers.controller;


import com.bootcamp.flavio.flavioserver.account.Account;
import com.bootcamp.flavio.flavioserver.account.service.AccountService;
import com.bootcamp.flavio.flavioserver.customers.Customer;
import com.bootcamp.flavio.flavioserver.customers.service.CustomerService;
import com.bootcamp.flavio.flavioserver.protocol.EndpointFailure;
import com.bootcamp.flavio.flavioserver.protocol.EndpointRequest;
import com.bootcamp.flavio.flavioserver.protocol.EndpointResponse;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController        
@CrossOrigin()
@RequestMapping("/customers")
public class CustomerController {
    
    @Autowired
        CustomerService customerService;
    
    @Autowired
        AccountService accountService;


    @PostMapping("/all") //En un banco no deberia existir.
    public EndpointResponse getAllCustomers(){
        var response = new EndpointResponse<List<Customer>>();
        response.setId("0");
        response.setMessage(customerService.getAllCustomers());
        return response;       
    }
    
    @PostMapping("/getbyid") 
    public EndpointResponse getCustomersById(@RequestBody EndpointRequest<Account> request){
        var response = new EndpointResponse<>();
        var failure = new EndpointFailure<String>();
        if (!(request.getMessage().getId() == null)) {
            var exist = customerService.existCustomerById(request.getMessage().getId());
            if(exist){
                
                response.setId("0");
                response.setMessage(customerService.getCustomerById(request.getMessage().getId()));
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
    
    @PostMapping("/delete")
    public EndpointResponse deleteCustomerById(@RequestBody EndpointRequest<Customer> request){
        var response = new EndpointResponse<>();
        var failure = new EndpointFailure<String>();
        
        if(!(request.getMessage().getId() == null)){
            boolean existCustomer = customerService.existCustomerById(request.getMessage().getId());
            if(existCustomer){
                
                response.setId("0");
                response.setMessage(request.getMessage().getId());
                customerService.deleteCustomerById(request.getMessage().getId());
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
    
    @PostMapping("/create")           
    public EndpointResponse createCustomer(@RequestBody EndpointRequest<Customer> request){
       var response = new EndpointResponse<>(); 
       var failure = new EndpointFailure<String>();
    
         try {
           Customer newCustomer = customerService.saveCustomer(request.getMessage()); 
           Account newAccount = new Account();
           newAccount.setBalance(BigDecimal.ZERO);
           newAccount.setAccountType("Current");
           newAccount.setCustomer(newCustomer);
           accountService.createAccount(newAccount);
           
           response.setId("0");
           response.setMessage(newCustomer.getId());
           
        } catch (Throwable e) {
           response.setId("1");
           response.setMessage(failure.getFailureMessage("9"));
            
        }
  
       return response;      
    }
    
    @PostMapping("/update")
    public EndpointResponse updateCustomer(@RequestBody EndpointRequest<Customer> request){    
        var response = new EndpointResponse<>();
             var failure = new EndpointFailure<String>();
        try {
            var existCustomer = customerService.existCustomerById(request.getMessage().getId());
            if (existCustomer) {
                
                Customer newUpdateCustomer = customerService.updateCustomer(request.getMessage(), request.getMessage().getId());
                response.setId("0");
                response.setMessage(newUpdateCustomer.getId()); 
            } else {
                
                response.setId("1");
                response.setMessage(failure.getFailureMessage("6"));  
                
            }
          
            
        } catch (Throwable e) {
            
            response.setId("1");
            response.setMessage(failure.getFailureMessage("1"));        
        }
        
        return response;
    }
    
}