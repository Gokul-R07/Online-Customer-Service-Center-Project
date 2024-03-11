package com.onlineCustomerServiceCenter.issue;
import com.onlineCustomerServiceCenter.customer.dao.CustomerRepository;
import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.*;
import com.onlineCustomerServiceCenter.customer.service.CustomerService;
import com.onlineCustomerServiceCenter.issue.dao.IssueRepository;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.issue.service.IssueService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class IssueTest {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    IssueService issueService;

    @Autowired
    IssueRepository issueRepository;
    Customer customer;
    Issue issue;

    @BeforeEach
    public void createCustomerIssue() {
        customer = new Customer("karthick", "spk@gmail.com", "gf@13412", "trichy", "9756456878");
        issue = new Issue("software", "login failure issue");
    }

    @AfterEach
    public void deleteCustomer() {
        customer = null;
        customerRepository.deleteAll();
        issue = null;
        issueRepository.deleteAll();
    }


    @Test
    @Order(1)
    public void addIssueToCustomer() throws CustomerNotFoundException {
        Assertions.assertNotNull(this.issueService.addIssueToCustomer(customer.getCustomerId(), issue));
    }


    @Test
    @Order(2)
    public void UpdateIssueDescription() {
        try {
            this.customerService.registerCustomer(customer);
            this.issueService.addIssueToCustomer(customer.getCustomerId(), issue);
            Assertions.assertNotNull(this.issueService.updateIssueDescriptionById(customer.getCustomerId(), issue.getIssueId(), "New updated issue description"));
        } catch (IssueNotFoundException | CustomerNotFoundException | CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void getAllIssues() {

        try {
            this.customerService.registerCustomer(customer);
            this.issueService.addIssueToCustomer(customer.getCustomerId(), issue);
            this.issueService.addIssueToCustomer(customer.getCustomerId(), issue);

            Assertions.assertNotNull(this.issueService.getAllIssues());
        } catch (CustomerNotFoundException | CustomerRegisterException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(4)
    public void getIssuesByCustomerId() {
        try {
            this.customerService.registerCustomer(customer);
            this.issueService.addIssueToCustomer(customer.getCustomerId(), issue);
            Assertions.assertNotNull(this.issueService.getAllIssuesByCustomerId(customer.getCustomerId()));
        } catch (CustomerNotFoundException | CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void deleteIssueById() {
        try {
            this.customerService.registerCustomer(customer);
            this.issueService.addIssueToCustomer(customer.getCustomerId(), issue);
            this.issueService.deleteIssueFromCustomer(customer.getCustomerId(), issue.getIssueId());

            Assertions.assertNull(this.issueService.updateIssueDescriptionById(customer.getCustomerId(), issue.getIssueId(), "New updated issue description"));
        } catch (IssueNotFoundException | CustomerNotFoundException | CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }
}


