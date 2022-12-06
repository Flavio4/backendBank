//
//package com.bootcamp.flavio.flavioserver.test.exchange;
//
//import com.bootcamp.flavio.flavioserver.account.Account;
//import com.bootcamp.flavio.flavioserver.account.AccountRepository;
//import com.bootcamp.flavio.flavioserver.protocol.EndpointRequest;
//import com.bootcamp.flavio.flavioserver.protocol.EndpointResponse;
//import java.math.BigDecimal;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//@RequestMapping("/testexchange")
public class TestExchangeController {}
//public class TestExchangeController {
//    //Repositorio de Exchange para el guardado en BD.
//    @Autowired
//    TestExchangeService testExchangeService;
//    
//    //Repositorio de account para sobre-escribir el balance en el modelo Account, llevar esto a Service
//    @Autowired
//    AccountRepository accountRepository;
//    
//    
//    @PostMapping("/deposit")        
//    public EndpointResponse exchangeDeposit(@RequestBody EndpointRequest<TestExchange> request){ 
//        var response = new EndpointResponse<String>();
//        try {
//            boolean exist = accountRepository.existsById(request.getMessage().getIdTarget());
//            boolean isAmmountPositive = (request.getMessage().getAmmount().compareTo(BigDecimal.ZERO) == 1);
//            //Existe o no, ammount positivo
//            if(exist && isAmmountPositive){
//            //Deposito
//                Account newBalance = accountRepository.findById(request.getMessage().getIdTarget()).get();
//                BigDecimal newAmmount = newBalance.getBalance().add(request.getMessage().getAmmount());
//                newBalance.setBalance(newAmmount);
//                accountRepository.save(newBalance);
//                
//            //Guardado de la transferencia
//                TestExchange newExchange = new TestExchange("deposit",
//                "PYG",
//                request.getMessage().getAmmount(),
//                "DD/MM/YY");
//                testExchangeService.saveExchange(newExchange);             
//                
//            //Response
//                response.setId("0");
//                response.setMessage("Transferencia completada");
//            }  
//        } catch (Exception e) {
//           response.setId("1");
//           response.setMessage("Deposito fallido, verificar datos ");
//         
//        }
//       
//       return  response;
//    }       
//       
//    //Transferencia interna
//    @PostMapping("/internaltransfer")  
//    public EndpointResponse internalTransfer(
//            @RequestBody EndpointRequest<TestExchange> request){
//        var response = new EndpointResponse<String>();
//        
//        try {
//            boolean existSource = accountRepository.existsById(request.getMessage().getIdSource());  
//            boolean existTarget = accountRepository.existsById(request.getMessage().getIdTarget());         
//            
//            boolean isAmmountPositive = (request.getMessage().ammount.compareTo(BigDecimal.ZERO) == 1);  // 0: es igual, 1: es mayor
//            //Existen o no
//            if (existSource && existTarget){
//               if(isAmmountPositive){
//                    //Transferencia
//                    Account sourceBalance = accountRepository.findById(request.getMessage().getIdSource()).get();
//                    Account targetBalance = accountRepository.findById(request.getMessage().getIdTarget()).get();
//                    //Validacion del balance del sender                  
//                    if(sourceBalance.getBalance().compareTo(request.getMessage().getAmmount()) >= 0){        
//                        sourceBalance.setBalance(sourceBalance.getBalance()
//                                .subtract(request.getMessage().getAmmount()));         //Resta el balance en el source
//                        targetBalance.setBalance(targetBalance.getBalance()
//                                .add(request.getMessage().getAmmount()));                 //Suma el balance en el target
//                    }else{
//                        response.setId("1");
//                        response.setMessage("Saldo insuficiente para la Transferencia");
//                        return response;
//                    }
//                    //Guardado de las transferencias
//                        //Sender                                                                                                                               //Falta fecha                     
//                    TestExchange newSenderExchange = new TestExchange();
//                    newSenderExchange.setTransactionType("in-transfer");
//                    newSenderExchange.setCurrency("PYG");
//                    newSenderExchange.setAmmount(request.getMessage().getAmmount().negate());
//                    newSenderExchange.setTransferDate("DD/MM/YY");
//                    testExchangeService.saveExchange(newSenderExchange);
//                       //Target
//                    TestExchange newTargetExchange = new TestExchange();
//                    newTargetExchange.setTransactionType("in-transfer");
//                    newTargetExchange.setCurrency("PYG");
//                    newTargetExchange.setAmmount(request.getMessage().getAmmount());
//                    newTargetExchange.setTransferDate("DD/MM/YY");
//                    testExchangeService.saveExchange(newTargetExchange); 
//                         
//                //Response
//                    response.setId("0");
//                    response.setMessage("Transferencia completada"); //id de transferencia
//                
//             }else{
//                response.setId("1");
//                response.setMessage("ERROR: Saldo en negativo");
//              }
//               
//               
//            }else{
//                response.setId("1");
//                response.setMessage("ERROR: Las cuentas no existen ");
//            }
//            
//            
//        } catch (Throwable e) {
//            response.setId("1");
//            response.setMessage("Algo a salido mal");
//        }
//        
//        return response; 
//    }
//            
//    @PostMapping("externaltransfer/receive")
//    public EndpointResponse testReceive(@RequestBody EndpointRequest<TestExchange> request){
//        var response = new EndpointResponse<String>();
//        boolean existTarget = accountRepository.existsById(request.getMessage().getIdTarget());
//        boolean isAmmountPositive = (request.getMessage().getAmmount().compareTo(BigDecimal.ZERO) == 1);
//        //Verificar si existe bank, nombre, docNumber, etc cuando las BD esten relacionadas
//        if(existTarget){
//            
//            Account targetAccount = accountRepository.findById(request.getMessage().getIdTarget()).get();
//            
//            if(isAmmountPositive){
//                //Actualizacion del balance del target
//                
//                BigDecimal newAmmount = targetAccount.getBalance().add(request.getMessage().getAmmount());
//                targetAccount.setBalance(newAmmount);
//                accountRepository.save(targetAccount);
//                
//                //Guardado de la transferencia 
//                TestExchange newTargetExchange = new TestExchange();
//                newTargetExchange.setTransactionType("ex-transfer");
//                newTargetExchange.setCurrency("PYG");
//                newTargetExchange.setAmmount(request.getMessage().getAmmount());
//                newTargetExchange.setTransferDate("DD/MM/YY");
//                testExchangeService.saveExchange(newTargetExchange); 
//                
//                //Response
//                response.setId("0");
//                response.setMessage("Transferencia completada");
//                
//            }else{
//                response.setId("1");
//                response.setMessage("El monto no puede ser negativo");
//            }
//            
//        }else{
//            response.setId("1");
//            response.setMessage("El cliente target con id " +request.getMessage().getIdTarget() + " no existe en ese banco");
//        }
//        
//        return response;
//        
//    }
//    
//    
//    @PostMapping("externaltransfer/send")
//    public EndpointResponse testSend(@RequestBody EndpointRequest<TestExchange> request){
//        var response = new EndpointResponse<String>();
//        boolean existSource = accountRepository.existsById(request.getMessage().getIdSource());
//        //Validar la existencia del cliente source.
//        if (existSource) {
//            
//             Account sourceAccount = accountRepository.findById(request.getMessage().getIdSource()).get();
//             
//             //Validacion de balance mayor o igual al ammount
//             if(sourceAccount.getBalance().compareTo(request.getMessage().getAmmount()) >= 0){
//                 
//                 //Enviar datos
//                String uri = "http://192.168.126.54:8080/exchange/receive";
//                RestTemplate restTemplate = new RestTemplate();
//                response = restTemplate.postForObject(uri, request, EndpointResponse.class);
//                
//                //Validar la respuesta
//                if("0".equals(response.getId())){
//                    
//                    //Actualizacion del balance del source
//                    BigDecimal newAmmount = sourceAccount.getBalance().subtract(request.getMessage().getAmmount());
//                    sourceAccount.setBalance(newAmmount);
//                    accountRepository.save(sourceAccount);
//                    
//                    //Guardado de la transferencia 
//                    TestExchange newSourceExchange = new TestExchange();
//                    newSourceExchange.setTransactionType("ex-transfer");
//                    newSourceExchange.setCurrency("PYG");
//                    newSourceExchange.setAmmount(request.getMessage().getAmmount().negate());        //negativo
//                    newSourceExchange.setTransferDate("DD/MM/YY");
//                    testExchangeService.saveExchange(newSourceExchange);
//                    
//                }
//                  
//             }else{
//                 response.setId("1");
//                 response.setMessage("Saldo insuficiente para la transferencia");
//             }
//                
//        }else{
//            response.setId("1");
//            response.setMessage("El cliente source con id " +request.getMessage().getIdSource()+ " no existe en este banco");
//        }
//        
//
//      return response;
//    }
//}






