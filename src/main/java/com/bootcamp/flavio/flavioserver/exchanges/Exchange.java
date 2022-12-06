
package com.bootcamp.flavio.flavioserver.exchanges;


import com.bootcamp.flavio.flavioserver.account.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;



@Entity
@Table(name = "Exchange")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String transactionType;
    
    @Column
    private String currency;
    
    @Column
    private BigDecimal ammount;
           
    @Column
    private String transferDate;   
    
    @Column
    private Long idSource;
            
    @Column
    private Long idTarget;
    
    @ManyToOne
    private Account account;
    
    @Column
    private Long bankIdSource;                 
    
    @Column
    private Long bankIdTarget;
   
    
    public Exchange() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmmount() {
        return ammount;
    }

    public void setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public Long getBankIdSource() {
        return bankIdSource;
    }

    public void setBankIdSource(Long bankIdSource) {
        this.bankIdSource = bankIdSource;
    }

    public Long getBankIdTarget() {
        return bankIdTarget;
    }

    public void setBankIdTarget(Long bankIdTarget) {
        this.bankIdTarget = bankIdTarget;
    }

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdTarget() {
        return idTarget;
    }

    public void setIdTarget(Long idTarget) {
        this.idTarget = idTarget;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


  
}