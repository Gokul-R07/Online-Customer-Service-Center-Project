package com.onlineCustomerServiceCenter.issue;

import com.onlineCustomerServiceCenter.customer.service.CustomerService;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.service.IssueService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class IssueTest {

    @Autowired
    IssueService issueService;

    @Autowired
    CustomerService customerService;

    @Test
    public void addIssueToCustomer(){
//        try{
//            Assertions.assertNotNull(this.customerService.addIssueToCustomer(1020, new Issue(101, "hardware", LocalDate.now().)));
//        }
//        catch(){
//
//        }
    }

}
