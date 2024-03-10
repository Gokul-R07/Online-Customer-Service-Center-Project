package com.onlineCustomerServiceCenter.customer.service;

import com.onlineCustomerServiceCenter.customer.dao.CustomerRepository;
import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.*;
import com.onlineCustomerServiceCenter.issue.dao.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Override
    public Customer registerCustomer(Customer newCustomer) throws CustomerRegisterException {
        Optional<Customer> customerOptional = this.customerRepository.findByEmail(newCustomer.getEmail());
        if(customerOptional.isPresent())
            throw new CustomerRegisterException("Email already registered, please re try.");
        return this.customerRepository.save(newCustomer);
    }
    @Override
    public Customer loginCustomer(String customerEmail,String customerPassword) throws CustomerLoginException {
        Optional<Customer> customerOptional = this.customerRepository.findByEmail(customerEmail);
        if(customerOptional.isEmpty())
         throw new CustomerLoginException("Customer does not exists for :"+customerEmail);

        Customer foundCustomer = customerOptional.get();
       if(!foundCustomer.getPassword().equals(customerPassword))
            throw new CustomerLoginException("Password does not match");

        return foundCustomer;
    }
    @Override
    public Customer updateCustomer(Customer customer) throws CustomerUpdateException {
        Optional<Customer> customerOptional = this.customerRepository.findByEmail(customer.getEmail());
        if(customerOptional.isEmpty())
            throw new CustomerUpdateException("Customer does not exists");
        customer.setCustomerId(customerOptional.get().getCustomerId());
        return this.customerRepository.save(customer);
    }
    @Override
    public List<Customer> getAllCustomers() throws CustomerNotFoundException {
        List<Customer> customerOptional=this.customerRepository.findAll();
        if(customerOptional.isEmpty())
            throw new CustomerNotFoundException("Customers not exist");
        return this.customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer customerId) throws CustomerNotFoundException {
        Optional<Customer> customerOptional = this.customerRepository.findById(customerId);

        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException("Customer does not exist for ID: "+customerId);
        }
        return  customerOptional.get();
    }

    @Override
    public Customer deleteCustomerById(Integer customerId) throws CustomerDeleteException {
        Optional<Customer> customerOptional = this.customerRepository.findById(customerId);

        if (customerOptional.isEmpty()) {
            throw new CustomerDeleteException("Customer does not exist for id:: " + customerId);
        }
        this.customerRepository.deleteById(customerId);
        return customerOptional.get();

    }

}





