/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.flavio.flavioserver.test;


public class TestModel {
    
    private long id;
    private String Name;
    private String docNumber;
    private String email;
    private String phoneNumber;

    public TestModel() {
    }
    
    public TestModel(long id, String Name, String docNumber, String email, String phoneNumber) {
        this.id = id;
        this.Name = Name;
        this.docNumber = docNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public TestModel(String Name, String docNumber, String email, String phoneNumber) {
        this.Name = Name;
        this.docNumber = docNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "TestModel{" + "id=" + id + ", Name=" + Name + ", docNumber=" + docNumber + ", email=" + email + ", phoneNumber=" + phoneNumber + '}';
    }
    
    
}
