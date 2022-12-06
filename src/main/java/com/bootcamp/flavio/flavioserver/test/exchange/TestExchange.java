
//package com.bootcamp.flavio.flavioserver.test.exchange;
//
//
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import jakarta.persistence.Transient;
//import java.math.BigDecimal;
//
//
//
public class TestExchange {}
//@Entity
//@Table(name = "ExchangeTest")
//public class TestExchange {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    Long id;
//    
//    @Column
//    String transactionType;
//    
//    @Column
//    String currency;
//    
//    @Column
//    BigDecimal ammount;
//           
//    @Column
//    String transferDate;   //cambiar
//    
//    @Transient
//    Long bankId;
//    
//    @Transient
//    Long idSource;
//            
//    @Transient
//    Long idTarget;
//    
//    @Transient
//    String targetName;
//    
//    @Transient
//    String sourceName;
//    
//    @Transient
//    String targetDocNumber;
//
//    public TestExchange() {
//    }
//
//    public TestExchange(Long id, String transactionType, String currency, BigDecimal ammount, String transferDate, Long bankId, Long idSource, Long idTarget, String targetName, String SourceName, String docNumber) {
//        this.id = id;
//        this.transactionType = transactionType;
//        this.currency = currency;
//        this.ammount = ammount;
//        this.transferDate = transferDate;
//        this.bankId = bankId;
//        this.idSource = idSource;
//        this.idTarget = idTarget;
//        this.targetName = targetName;
//        this.sourceName = SourceName;
//        this.targetDocNumber = docNumber;
//    }
//
//    public TestExchange(String transactionType, String currency, BigDecimal ammount, String transferDate, Long bankId, Long idSource, Long idTarget, String targetName, String SourceName, String docNumber) {
//        this.transactionType = transactionType;
//        this.currency = currency;
//        this.ammount = ammount;
//        this.transferDate = transferDate;
//        this.bankId = bankId;
//        this.idSource = idSource;
//        this.idTarget = idTarget;
//        this.targetName = targetName;
//        this.sourceName = SourceName;
//        this.targetDocNumber = docNumber;
//    }
//
//    public TestExchange(String transactionType, String currency, BigDecimal ammount, String transferDate) {
//        this.transactionType = transactionType;
//        this.currency = currency;
//        this.ammount = ammount;
//        this.transferDate = transferDate;
//    }
//
//    
//        
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTransactionType() {
//        return transactionType;
//    }
//
//    public void setTransactionType(String transactionType) {
//        this.transactionType = transactionType;
//    }
//
//    public String getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(String currency) {
//        this.currency = currency;
//    }
//
//    public BigDecimal getAmmount() {
//        return ammount;
//    }
//
//    public void setAmmount(BigDecimal ammount) {
//        this.ammount = ammount;
//    }
//
//    public String getTransferDate() {
//        return transferDate;
//    }
//
//    public void setTransferDate(String transferDate) {
//        this.transferDate = transferDate;
//    }
//
//    public Long getBankId() {
//        return bankId;
//    }
//
//    public void setBankId(Long bankId) {
//        this.bankId = bankId;
//    }
//
//    public Long getIdSource() {
//        return idSource;
//    }
//
//    public void setIdSource(Long idSource) {
//        this.idSource = idSource;
//    }
//
//    public Long getIdTarget() {
//        return idTarget;
//    }
//
//    public void setIdTarget(Long idTarget) {
//        this.idTarget = idTarget;
//    }
//
//    public String getTargetName() {
//        return targetName;
//    }
//
//    public void setTargetName(String targetName) {
//        this.targetName = targetName;
//    }
//
//    public String getSourceName() {
//        return sourceName;
//    }
//
//    public void setSourceName(String SourceName) {
//        this.sourceName = SourceName;
//    }
//
//    public String getDocNumber() {
//        return targetDocNumber;
//    }
//
//    public void setDocNumber(String docNumber) {
//        this.targetDocNumber = docNumber;
//    }
//
//    
//    
//    
//    
//    
//  
//}
