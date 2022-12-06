
package com.bootcamp.flavio.flavioserver.customers;



import com.bootcamp.flavio.flavioserver.account.Account;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;



@Entity
@Table(name = "customer")
public class Customer {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    
    @Column(name = "fisrtName",
            nullable = false,
            columnDefinition = "TEXT")
    private String firstName;
    
    @Column(name = "lastName",
            nullable = false,
            columnDefinition = "TEXT")
    private String lastName;
   
    @Column(name = "Docnumber",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true)
    private String docNumber;
    
    @Column(name = "Doctype",
            columnDefinition = "TEXT")
    private String docType;
    
    @Column(name = "Email",
            columnDefinition = "TEXT")
    private String email;
    
    @Column(name = "Phonenumber",
            columnDefinition = "TEXT"
    )
    private String phoneNumber;
    
    @Column(name = "Address",
            columnDefinition = "TEXT")
    private String address;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> account;
    
    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}

