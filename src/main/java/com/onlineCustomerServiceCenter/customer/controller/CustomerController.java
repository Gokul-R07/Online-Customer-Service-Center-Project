package com.onlineCustomerServiceCenter.customer.controller;

import com.onlineCustomerServiceCenter.customer.dto.CustomerLoginDto;
import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerDeleteException;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerLoginException;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerRegisterException;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerUpdateException;
import com.onlineCustomerServiceCenter.customer.service.CustomerService;
import com.onlineCustomerServiceCenter.issue.IssueService;
import com.onlineCustomerServiceCenter.solution.entity.Solution;
import com.onlineCustomerServiceCenter.solution.exceptions.SolutionException;
import com.onlineCustomerServiceCenter.solution.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SolutionService solutionService;


    @PostMapping("customer")
    public Customer registerCustomer(@RequestBody Customer newCustomer) throws CustomerRegisterException {
        return this.customerService.registerCustomer(newCustomer);
    }

    @PostMapping("login/customer")
    public Customer loginCustomer(@RequestBody CustomerLoginDto loginDto) throws CustomerLoginException {
        return this.customerService.loginCustomer(loginDto.getCustomerEmail(), loginDto.getCustomerPassword());
    }

    @PutMapping("update/customer")
    public Customer updateCustomerProfile(@RequestBody Customer customer) throws CustomerUpdateException {
        return this.customerService.updateCustomer(customer);
    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers() {
        return this.customerService.getAllCustomers();
    }

    @GetMapping("customer/{id}")
    public Customer getCustomerById(@PathVariable("id") Integer id) {
        return this.customerService.getCustomerById(id);
    }

    @DeleteMapping("customer/{id}")
    public Customer deleteCustomerById(@PathVariable("id") Integer id) throws CustomerDeleteException {
        return this.customerService.deleteCustomerById(id);
    }
    @PatchMapping("customer")
    public Solution acceptSolution(@RequestParam Integer solutionId) throws SolutionException{
         return this.solutionService.acceptSolution(solutionId);
    }
}

