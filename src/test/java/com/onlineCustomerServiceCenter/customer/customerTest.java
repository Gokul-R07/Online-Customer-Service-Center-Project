package com.onlineCustomerServiceCenter.customer;

import com.onlineCustomerServiceCenter.customer.dao.CustomerRepository;
import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;


@SpringBootTest
public class customerTest {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    public void importData() {

    }
}