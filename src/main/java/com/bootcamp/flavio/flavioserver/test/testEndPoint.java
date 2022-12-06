/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.flavio.flavioserver.test;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testEndPoint {
    
    @Autowired
    TestService testService;
    
    @GetMapping("api/test/get")
    public List<TestModel> getTest(){
       return testService.getTest();
    }

    @PostMapping("api/test/post")
    public TestModel postTest(@RequestBody TestModel testmodel){
        return testmodel;        
    }
}
