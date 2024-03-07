package com.onlineCustomerServiceCenter.customer.service;

import com.onlineCustomerServiceCenter.customer.exceptions.*;
import com.onlineCustomerServiceCenter.customer.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer registerCustomer(Customer newCustomer) throws CustomerRegisterException;
    Customer loginCustomer(String customerEmail,String customerPassword) throws CustomerLoginException;
    Customer updateCustomer(Customer customer) throws CustomerUpdateException;
    List<Customer> getAllCustomers() throws CustomerNotFoundException;
    Customer getCustomerByEmail(String customerEmail) throws CustomerNotFoundException;
    Customer deleteCustomerByEmail(String email) throws CustomerDeleteException;}



