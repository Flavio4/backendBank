
package com.bootcamp.flavio.flavioserver.customers.service;


import com.bootcamp.flavio.flavioserver.customers.Customer;
import com.bootcamp.flavio.flavioserver.customers.repository.CustomerRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CustomerService {
    
    @Autowired
    CustomerRepository customerRepository;
    
    //Metodos para el controlador
      public List<Customer> getAllCustomers(){
         return customerRepository.findAll();             
    }
      
      public Customer getCustomerById(Long id){
          return customerRepository.findById(id).get();                //Metodo para obtener e1 cliente con el id.
      }

      
      public void deleteCustomerById(Long id) {
           customerRepository.deleteById(id);
      }
        
      public boolean existCustomerById(Long id){
          return customerRepository.existsById(id);
      }

      
      public Customer saveCustomer(Customer customer) { 
            return customerRepository.save(customer);
      }

      public Customer updateCustomer(Customer customer, long id) {
          Customer customerDB = customerRepository.findById(id).get();
          
          if (Objects.nonNull(customer.getFirstName()) && !"".equalsIgnoreCase(customer.getFirstName())) {
              
                customerDB.setFirstName(customer.getFirstName());
            
          }
          
          if (Objects.nonNull(customer.getLastName()) && !"".equalsIgnoreCase(customer.getLastName())) {
              
               customerDB.setLastName(customer.getLastName());
           
         }
           
          if (Objects.nonNull(customer.getDocNumber()) && !"".equalsIgnoreCase(customer.getDocNumber())) {
              
                customerDB.setDocNumber(customer.getDocNumber());
            
          }
            
          if (Objects.nonNull(customer.getDocType()) && !"".equalsIgnoreCase(customer.getDocType())) {
              
                customerDB.setDocType(customer.getDocType());
            
          }
             
          if (Objects.nonNull(customer.getEmail()) && !"".equalsIgnoreCase(customer.getEmail())) {
              
                customerDB.setEmail(customer.getEmail());
            
          }
              
          if (Objects.nonNull(customer.getPhoneNumber()) && !"".equalsIgnoreCase(customer.getPhoneNumber())) {
              
                customerDB.setPhoneNumber(customer.getPhoneNumber());
            
          }
               
          if (Objects.nonNull(customer.getAddress()) && !"".equalsIgnoreCase(customer.getAddress())) {
              
                customerDB.setAddress(customer.getAddress());
            
          }
      
        return customerRepository.save(customerDB);
      }
      
      
}
