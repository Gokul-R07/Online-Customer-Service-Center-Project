package com.onlineCustomerServiceCenter.customer.service;

import com.onlineCustomerServiceCenter.customer.exceptions.CustomerRegisterException;
import com.onlineCustomerServiceCenter.customer.dao.CustomerRepository;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerUpdateException;
import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerDeleteException;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerLoginException;
import com.onlineCustomerServiceCenter.issue.IssueRepository;
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
        Optional<Customer> customerOpt = this.customerRepository.findById(customer.getCustomerId());
        if(customerOpt.isEmpty())
            throw new CustomerUpdateException("Customer does not exists for :"+customer.getCustomerId());
        return this.customerRepository.save(customer);
    }
    @Override
    public List<Customer> getAllCustomers(){
        return this.customerRepository.findAll();
    }
    @Override
    public Customer getCustomerById(Integer customerId){
        return this.customerRepository.findById(customerId).get();
    }


   @Override
   public Customer deleteCustomerById(Integer customerId) throws CustomerDeleteException {
        Optional<Customer> customerOpt = this.customerRepository.findById(customerId);

        if (customerOpt.isEmpty()) {
            throw new CustomerDeleteException("Customer does not exist for ID: " + customerId);
        }
            this.customerRepository.deleteById(customerId);
            Customer customerToBeDeleted= customerOpt.get();
            return  customerToBeDeleted;
    }
}
