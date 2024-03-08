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
        Optional<Customer> customerOpt = this.customerRepository.findByEmail(newCustomer.getEmail());
        if(customerOpt.isPresent())
            throw new CustomerRegisterException("Email already registered, please re try."+newCustomer.getEmail());
        return this.customerRepository.save(newCustomer);
    }
    @Override
    public Customer loginCustomer(String customerEmail,String customerPassword) throws CustomerLoginException {
        Optional<Customer> customerOpt = this.customerRepository.findByEmail(customerEmail);
        if(customerOpt.isEmpty())
         throw new CustomerLoginException("Customer does not exists for :"+customerEmail);

        Customer foundCustomer = customerOpt.get();
       if(!foundCustomer.getPassword().equals(customerPassword))
            throw new CustomerLoginException("Password does not match");

        return foundCustomer;
    }
    @Override
    public Customer updateCustomer(Customer customer) throws CustomerUpdateException {
        Optional<Customer> customerOpt = this.customerRepository.findByEmail(customer.getEmail());
        if(customerOpt.isEmpty())
            throw new CustomerUpdateException("Customer does not exists for :"+customer.getCustomerId());
        customer.setCustomerId(customerOpt.get().getCustomerId());
        return this.customerRepository.save(customer);
    }
    @Override
    public List<Customer> getAllCustomers() throws CustomerNotFoundException {
        List<Customer> customerOpt=this.customerRepository.findAll();
        if(customerOpt.isEmpty())
            throw new CustomerNotFoundException("Customers not exist");
        return this.customerRepository.findAll();
    }

    @Override
    public Customer getCustomerByEmail(String customerEmail) throws CustomerNotFoundException {
        Optional<Customer> customerOpt = this.customerRepository.findByEmail(customerEmail);

        if (customerOpt.isEmpty()) {
            throw new CustomerNotFoundException("Customer does not exist for email:: " + customerEmail);
        }
        return  customerOpt.get();
    }

    @Override
    public Customer deleteCustomerByEmail(String email) throws CustomerDeleteException {
        Optional<Customer> customerOpt = this.customerRepository.findByEmail(email);

        if (customerOpt.isEmpty()) {
            throw new CustomerDeleteException("Customer does not exist for email:: " + email);
        }
        this.customerRepository.deleteById(customerOpt.get().getCustomerId());
        Customer customerToBeDeleted= customerOpt.get();
        return  customerToBeDeleted;
    }


}





