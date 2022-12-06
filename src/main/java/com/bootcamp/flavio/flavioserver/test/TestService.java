
package com.bootcamp.flavio.flavioserver.test;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class TestService {
    
    public List<TestModel> getTest(){
       
        TestModel testmodel = new TestModel("Customer Test",
                "6111393",
                "testemail@gmail.com",
                "0982235199");             //Insertar logica aqui
        
        return List.of(testmodel);
    }
  }
