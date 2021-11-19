package com.luv2code.springdemo.rest;


import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @RequestMapping(value = "/customers/{customer_id}", method = RequestMethod.GET)
    public Customer gerCustomer(@PathVariable int customer_id) {
        Customer customer = customerService.getCustomer(customer_id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer_id not found - " + customer_id);
        }
        return customer;
    }


    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public Customer addCustomer(@RequestBody Customer customer) {
        System.out.println("Customer ID before setting to ZERO: " + customer.getId());
        customer.setId(0); // THIS IS REQUIRED to make ID empty (null or 0) so saveOrUpdate DAO method will perform INSERT using Hibernate, because CLIENT can send ID in his HTTP POST request
        customerService.saveCustomer(customer);
        return customer;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.PUT)
    public Customer updateCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return customer;
    }


    @RequestMapping(value = "/customers/{customer_id}", method = RequestMethod.DELETE)
    public String deleteCustomer(@PathVariable int customer_id)  {
        Customer customer = customerService.getCustomer(customer_id);
        if (customer == null)   {
            throw new CustomerNotFoundException("Customer_id not found - " + customer_id);
        }
        customerService.deleteCustomer(customer_id);
        return "Customer was deleted with ID: " +customer_id;
    }

}
