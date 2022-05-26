package com.codeclan.example.CourseBooking.controllers;

import com.codeclan.example.CourseBooking.models.Customer;
import com.codeclan.example.CourseBooking.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> allCustomersAndCustomerByFilters(
            @RequestParam(name="courseName", required = false) String courseName){
        if(courseName == null){
            return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(customerRepository.findByBookingsCourseName(courseName), HttpStatus.OK);
    }

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity findCustomerById(@PathVariable Long id){
        return new ResponseEntity(customerRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<Customer> addNewCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping(value = "/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer currentCustomer = customerRepository.findById(id).get();
        currentCustomer.setName(updatedCustomer.getName());
        currentCustomer.setAge(updatedCustomer.getAge());
        currentCustomer.setTown(updatedCustomer.getTown());

        customerRepository.save(currentCustomer);

        return new ResponseEntity<>(currentCustomer, HttpStatus.CREATED);
    }
}
