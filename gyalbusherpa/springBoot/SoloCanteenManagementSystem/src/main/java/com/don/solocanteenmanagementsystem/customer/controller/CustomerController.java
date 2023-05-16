package com.don.solocanteenmanagementsystem.customer.controller;

import com.don.solocanteenmanagementsystem.customer.dto.CustomerRequestDto;
import com.don.solocanteenmanagementsystem.customer.model.Customer;
import com.don.solocanteenmanagementsystem.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/customers")
    public String createNewCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        long id = customerService.createCustomer(customerRequestDto);
        return "Customer with id " + id + " created successfully";
    }

    @GetMapping("/customers/{id}")
    public Customer fetchCustomerById(@PathVariable long id) {
        return customerService.findCustomerById(id);
    }

}

