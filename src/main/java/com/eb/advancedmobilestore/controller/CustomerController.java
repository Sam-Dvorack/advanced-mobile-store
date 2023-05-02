package com.eb.advancedmobilestore.controller;


import com.eb.advancedmobilestore.model.Customer;
import com.eb.advancedmobilestore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value="/get-customers", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomers(){
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }
}
