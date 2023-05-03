package com.eb.advancedmobilestore.service;

import com.eb.advancedmobilestore.model.Customer;
import com.eb.advancedmobilestore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer setCustomerStatus(Customer customer) { return customerRepository.save(customer); }

    public List<Customer> getCustomers(){return customerRepository.findAll();}

//    public Optional<Customer> getCustomer(Long id){
//       return customerRepository.findById(id);
//    }

    public Customer getCustomer(String emailAddress){
        return customerRepository.findCustomerByEmailAddress(emailAddress);
    }

    public void removeCustomer(Long id){
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Long id, Customer customerDetails){
        Customer customer = customerRepository.findById(id).get();
        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());
        customer.setEmailAddress(customerDetails.getEmailAddress());
        return customerRepository.save(customer);
    }




}
