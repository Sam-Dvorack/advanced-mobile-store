package com.eb.advancedmobilestore.controller;


import com.eb.advancedmobilestore.enums.Status;
import com.eb.advancedmobilestore.model.Customer;
import com.eb.advancedmobilestore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public Status login(@RequestParam String emailAddress, @RequestParam String password){
        List<Customer> customers = customerService.getCustomers();
        for (Customer customer : customers) {
            if (customer.getEmailAddress().equals(emailAddress) && customer.getPassword().equals(password)) {
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

    @RequestMapping(value="/get-customer", method = RequestMethod.GET)
    public Status getCustomer(@RequestParam String emailAddress){
        List<Customer> customers = customerService.getCustomers();
        for (Customer customer : customers) {
            if (customer.getEmailAddress().equals(emailAddress)) {
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

    @RequestMapping(value="/get-customers", method = RequestMethod.GET)
    public Status getCustomers(){
        List<Customer> customers = customerService.getCustomers();

            if (!customers.isEmpty()) {
                return Status.SUCCESS;

        }
        return Status.FAILURE;
    }

    @PostMapping("/customers/register")
    public Status registerUser(@RequestBody Customer newCustomer) {
        List<Customer> customers = customerService.getCustomers();
        System.out.println("New customer: " + newCustomer.toString());
        for (Customer customer : customers) {
            System.out.println("Registered user: " + newCustomer.toString());
            if (customer.equals(newCustomer)) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }
        customerService.createCustomer(newCustomer);
        return Status.SUCCESS;
    }

    @PostMapping("/customers/login")
    public Status loginUser(@RequestBody Customer customer) {
        List<Customer> customers = customerService.getCustomers();
        for (Customer other : customers) {
            if (other.equals(customer)) {
                customer.setLoggedIn(true);
                customerService.setCustomerStatus(customer);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

    @PostMapping("/users/logout")
    public Status logUserOut(@RequestBody Customer customer) {
        List<Customer> customers = customerService.getCustomers();
        for (Customer other : customers) {
            if (other.equals(customer)) {
                customer.setLoggedIn(false);
                customerService.setCustomerStatus(customer);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

}
