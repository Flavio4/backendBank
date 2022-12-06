
package com.bootcamp.flavio.flavioserver.exchanges.controller;


import com.bootcamp.flavio.flavioserver.exchanges.service.ExchangeService;
import com.bootcamp.flavio.flavioserver.account.Account;
import com.bootcamp.flavio.flavioserver.account.service.AccountService;
import com.bootcamp.flavio.flavioserver.bank.Bank;
import com.bootcamp.flavio.flavioserver.bank.myBank.MyBank;
import com.bootcamp.flavio.flavioserver.bank.service.BankService;
import com.bootcamp.flavio.flavioserver.exchanges.Exchange;
import com.bootcamp.flavio.flavioserver.protocol.EndpointFailure;
import com.bootcamp.flavio.flavioserver.protocol.EndpointRequest;
import com.bootcamp.flavio.flavioserver.protocol.EndpointResponse;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
 
    @Autowired
    ExchangeService exchangeService;
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    BankService bankService;
    
    
    @PostMapping("/getbyaccount")
     public EndpointResponse getByAccountId(@RequestBody EndpointRequest<Account> request){
         var response = new EndpointResponse<>();
         var failure = new EndpointFailure<String>();
         var existAccount = accountService.existAccountById(request.getMessage().getId());
         if (existAccount) {
            var exchangeList = exchangeService.getExchangesByAccount(request.getMessage());
            if(!exchangeList.isEmpty()){
         
                response.setId("0");
                response.setMessage(exchangeList);
            }else{
             
                response.setId("1");
                response.setMessage(failure.getFailureMessage("7"));
            }
             
        } else {
             
             response.setId("1");
             response.setMessage(failure.getFailureMessage("2"));
        }
           
         return response;
     }
     
     
    @PostMapping("/deposit")        
    public EndpointResponse exchangeDeposit(@RequestBody EndpointRequest<Exchange> request){ 
        var response = new EndpointResponse<>();
        var failure = new EndpointFailure<String>();    
        
        boolean existAccount = accountService.existAccountById(request.getMessage().getIdTarget());
        boolean isAmmountPositive = (request.getMessage().getAmmount().compareTo(BigDecimal.ZERO) == 1);
        
            //Existe o no, ammount positivo
            if(existAccount && isAmmountPositive){
                //Deposito
                Account targetAccount = accountService.getAccountById(request.getMessage().getIdTarget());
                accountService.updateBalanceTargetAccount(targetAccount, request.getMessage().getAmmount());
                
                //Guardado de la transferencia
                var newExchange = exchangeService.saveTargetExchange(targetAccount,request.getMessage() , "DEPOSIT");
           
                //Response
                response.setId("0");
                response.setMessage(newExchange.getId());
                
            }else{
                if (existAccount) {
                    response.setId("1");
                    response.setMessage(failure.getFailureMessage("4"));
                } else {
                    response.setId("1");
                    response.setMessage(failure.getFailureMessage("2"));
                }
            }
            
       return  response;
    }       
       
    
    
    @PostMapping("/withdraw")
    public EndpointResponse withdraw(@RequestBody EndpointRequest<Exchange> request){
        var response = new EndpointResponse<>();
        var failure = new EndpointFailure<String>(); 
        
        boolean existAccount = (accountService.existAccountById(request.getMessage().getIdSource()));
        boolean isAmmountPositive = (request.getMessage().getAmmount().compareTo(BigDecimal.ZERO) == 1);
        
        if(existAccount && isAmmountPositive){
           Account sourceAccount = accountService.getAccountById(request.getMessage().getIdSource());
            //Validacion: el valor a retirar menor o igual al balance
            boolean isWithdrawable = (sourceAccount.getBalance().compareTo(request.getMessage().getAmmount()) >= 0);
            if(isWithdrawable){
                //Retiro
                accountService.updateBalanceSourceAccount(sourceAccount, request.getMessage().getAmmount());
               
                //Guardado de transferencia
                var newExchange =  exchangeService.saveSourceExchange(sourceAccount, request.getMessage(), "WITHDRAW");
                newExchange.setIdSource(request.getMessage().getIdSource());
                response.setId("0");
                response.setMessage(newExchange.getId());
            
            }else{
                response.setId("1");
                response.setMessage(failure.getFailureMessage("5"));
            }
        }else{
            if (existAccount) {
               response.setId("1");
               response.setMessage(failure.getFailureMessage("4"));
           }else{
                response.setId("1");
                response.setMessage(failure.getFailureMessage("2"));   
           }
        
        }
        return response;
    }
    
    
    
    
    //Transferencia interna
    @PostMapping("/transfer")  
    public EndpointResponse internalTransfer(@RequestBody EndpointRequest<Exchange> request){
        var response = new EndpointResponse<>();
        var failure = new EndpointFailure<String>(); 
        boolean existSource = accountService.existAccountById(request.getMessage().getIdSource());  
        boolean existTarget = accountService.existAccountById(request.getMessage().getIdTarget());         
            
        boolean isAmmountPositive = (request.getMessage().getAmmount().compareTo(BigDecimal.ZERO) == 1);  // 0: es igual, 1: es mayor
        
            //Existen o no, verificar que son cuentas diferentes
            if ( existSource && existTarget ){
                Account sourceAccount = accountService.getAccountById(request.getMessage().getIdSource());
                Account targetAccount = accountService.getAccountById(request.getMessage().getIdTarget());
               if(isAmmountPositive && !(sourceAccount.getId().equals(targetAccount.getId()))){
                    //Validacion del balance del source                 
                    if((sourceAccount.getBalance().compareTo(request.getMessage().getAmmount()) >= 0)){
                        //Transferencia
                       accountService.updateBalanceTargetAccount(targetAccount, request.getMessage().getAmmount());      //Suma el balance en el target
                       accountService.updateBalanceSourceAccount(sourceAccount, request.getMessage().getAmmount());      //Resta el balance en el source
                                       
                    }else{
                        response.setId("1");
                        response.setMessage(failure.getFailureMessage("5"));
                        return response;
                    }
                    //Guardado de las transferencias                                                                                                                                          
                    Exchange newSourceExchange =  exchangeService.saveSourceExchange(sourceAccount, request.getMessage(), "IN-TRANSFER");
                    Exchange newTargetExchange =  exchangeService.saveTargetExchange(targetAccount, request.getMessage(), "IN-TRANSFER"); 
                         

                    Long[] exchangesId = {newSourceExchange.getId(), newTargetExchange.getId()};
                    response.setId("0");
                    response.setMessage(exchangesId);    //ids de las transferencias
                
                }else{
                   if (isAmmountPositive) {
                       response.setId("1");
                       response.setMessage(failure.getFailureMessage("8"));
                   } else {
                       response.setId("1");
                       response.setMessage(failure.getFailureMessage("4"));
                   }     
                }
            }else{
                response.setId("1");
                response.setMessage(failure.getFailureMessage("2"));
            }
        
        return response; 
    }
            
    
    @PostMapping("/receive")
    public EndpointResponse receive(@RequestBody EndpointRequest<Exchange> request){
        var response = new EndpointResponse<>();
        var failure = new EndpointFailure<String>();
        boolean existBankSource = bankService.existBankById(request.getMessage().getBankIdSource());
        
        //Verificacion, de CONEXION entre bancos
        if(existBankSource && (request.getMessage().getBankIdTarget() == MyBank.getMyBankId())){
            
            boolean isAmmountPositive = (request.getMessage().getAmmount().compareTo(BigDecimal.ZERO) == 1);
            boolean existAccountTarget = accountService.existAccountById(request.getMessage().getIdTarget());
            
            if(isAmmountPositive && existAccountTarget){                  
                Account targetAccount = accountService.getAccountById(request.getMessage().getIdTarget());
                //Actualizacion del balance del target
                accountService.updateBalanceTargetAccount(targetAccount, request.getMessage().getAmmount());
                //Guardado de la transferencia 
                exchangeService.saveTargetExchange(targetAccount, request.getMessage(), "EX-TRANSFER");
                //Response
                response.setId("0");
                response.setMessage("OK");
                
            }else{
                if(isAmmountPositive){
                    response.setId("1");
                    response.setMessage(failure.getFailureMessage("2"));
                }else{
                    response.setId("1");
                    response.setMessage(failure.getFailureMessage("4"));
                }
            }
        }else{
            response.setId("1");
            response.setMessage(failure.getFailureMessage("10"));
        }
        
        return response;
        
    }
    
    
    @PostMapping("/send")
    public EndpointResponse send(@RequestBody EndpointRequest<Exchange> request){
        var response = new EndpointResponse<>();
        var failure = new EndpointFailure<String>();
        boolean existAccountSource = accountService.existAccountById(request.getMessage().getIdSource());
        boolean existTargetBank = bankService.existBankById(request.getMessage().getBankIdTarget());
        
        //Validar la existencia del cliente source y el banco target en bd
        if (existAccountSource && existTargetBank) {
            
             Account sourceAccount = accountService.getAccountById(request.getMessage().getIdSource());
             Bank targetBank = bankService.getBankById(request.getMessage().getBankIdTarget());
             boolean isTransferible = sourceAccount.getBalance().compareTo(request.getMessage().getAmmount()) >= 0;
             boolean isAmmountPositive = (request.getMessage().getAmmount().compareTo(BigDecimal.ZERO) == 1);
             
             //Validacion de balance mayor o igual al ammount, amount distinto de nulo o cero.
             if(isTransferible && isAmmountPositive){
                 
                 //Enviar datos
                String uri = targetBank.getUrl();                                    
                RestTemplate restTemplate = new RestTemplate();
                request.getMessage().setBankIdSource(MyBank.getMyBankId());
                response = restTemplate.postForObject(uri, request, EndpointResponse.class);
 
                //Validar la respuesta
                if("0".equals(response.getId())){
                    
                    //Actualizacion del balance del source
                    accountService.updateBalanceSourceAccount(sourceAccount, request.getMessage().getAmmount());
                    
                    //Guardado de la transferencia 
                    Exchange newExchange= exchangeService.saveSourceExchange(sourceAccount, request.getMessage(), "EX-TRANSFER");
                    
                    response.setId("0");
                    response.setMessage(newExchange.getId());
                }
                
             }else{
                 if (isTransferible) {
                    response.setId("1");
                    response.setMessage(failure.getFailureMessage("4"));
                 }else{
                    response.setId("1");
                    response.setMessage(failure.getFailureMessage("5"));
                 }
                 
             }
                
        }else{
            if(existAccountSource){
                response.setId("1");
                response.setMessage(failure.getFailureMessage("3"));
            }else{
                response.setId("1");
                response.setMessage(failure.getFailureMessage("6"));
            }
            
        }
      return response;
    }
}


