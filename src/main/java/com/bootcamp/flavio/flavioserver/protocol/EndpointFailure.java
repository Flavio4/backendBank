
package com.bootcamp.flavio.flavioserver.protocol;

public class EndpointFailure<M> extends EndpointEnvelope<M>{
 
    public EndpointFailure getFailureMessage(String errorId){
        var failureResponse = new EndpointFailure<>();
        failureResponse.setId(errorId);
        
        if(failureResponse.getId().equals("1")){
            failureResponse.setMessage("Campos nulos");
        }
        if(failureResponse.getId().equals("2")){
            failureResponse.setMessage("No existe la cuenta");
        }
        if(failureResponse.getId().equals("3")){
            failureResponse.setMessage("Error de conexion con el banco");
        }
        if(failureResponse.getId().equals("4")){
            failureResponse.setMessage("Monto negativo o igual a cero");
        }
        if(failureResponse.getId().equals("5")){
            failureResponse.setMessage("Saldo insuficiente");
        }
        if(failureResponse.getId().equals("6")){
            failureResponse.setMessage("No existe el cliente");
        }
        if(failureResponse.getId().equals("7")){
            failureResponse.setMessage("No existe transferencias");
        }
        if(failureResponse.getId().equals("8")){
            failureResponse.setMessage("Cuentas Iguales");
        }
        if(failureResponse.getId().equals("9")){
            failureResponse.setMessage("Error de validacion de datos");
        }
        if(failureResponse.getId().equals("10")){
            failureResponse.setMessage("Solicitud Rechazada");
        }
        
      return failureResponse;
    }
    
}
