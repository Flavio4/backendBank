
package com.bootcamp.flavio.flavioserver.bank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table
public class Bank {
    
   @Id
   @Column
   private Long bankNumber;
   
   @Column(name = "BankName")
   private String bankName;
   
   @Column
   private String url;

   
   
    public Bank() {
    }


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(Long bankNumber) {
        this.bankNumber = bankNumber;
    }

    @Override
    public String toString() {
        return "Bank{" + "bankNumber=" + bankNumber + ", bankName=" + bankName + ", url=" + url + '}';
    }


  
  
}
