package com.onlineCustomerServiceCenter.customer.service;

import com.onlineCustomerServiceCenter.customer.exceptions.CustomerRegisterException;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerUpdateException;
import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerDeleteException;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerLoginException;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerService {
    Customer registerCustomer(Customer newCustomer) throws CustomerRegisterException;
    Customer loginCustomer(String customerEmail,String customerPassword) throws CustomerLoginException;
    Customer updateCustomer(Customer customer) throws CustomerUpdateException;
    List<Customer> getAllCustomers();
    Customer getCustomerById(Integer customerId);
    Customer deleteCustomerById(Integer customerId) throws CustomerDeleteException;


}
