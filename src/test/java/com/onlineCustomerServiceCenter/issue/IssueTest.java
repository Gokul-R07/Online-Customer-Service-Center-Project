package com.onlineCustomerServiceCenter.issue;
import com.onlineCustomerServiceCenter.customer.dao.CustomerRepository;
import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.*;
import com.onlineCustomerServiceCenter.customer.service.CustomerService;
import com.onlineCustomerServiceCenter.issue.dao.IssueRepository;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.issue.exception.NullIssueException;
import com.onlineCustomerServiceCenter.issue.service.IssueService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void addIssueToCustomer() throws CustomerNotFoundException, CustomerRegisterException {
        this.customerService.registerCustomer(customer);
        this.issueService.addIssueToCustomer(customer.getCustomerId(), issue);
        Assertions.assertNotNull(customer.getIssues());
    }

    @Test
    @Order(2)
    public void customerHasEmptyIssueList() throws  CustomerRegisterException {
        this.customerService.registerCustomer(customer);
       assertTrue(customer.getIssues().isEmpty());

    }


    @Test
    @Order(3)
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
    @Order(4)
    public void updateIssueDescriptionWithWrongIssueId() {
        try {
            this.customerService.registerCustomer(customer);
            this.issueService.addIssueToCustomer(customer.getCustomerId(), issue);

            assertThrows(IssueNotFoundException.class, () -> {
                this.issueService.updateIssueDescriptionById(customer.getCustomerId(), 290, "New updated issue description"); // Call the method that should throw the exception
            });

        } catch (CustomerNotFoundException | CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void updateIssueDescriptionWithCustomerId() {
        try {
            this.customerService.registerCustomer(customer);
            this.issueService.addIssueToCustomer(customer.getCustomerId(), issue);

            assertThrows(CustomerNotFoundException.class, () -> {
                this.issueService.updateIssueDescriptionById(102, issue.getIssueId(), "New updated issue description"); // Call the method that should throw the exception
            });

        } catch (CustomerRegisterException | CustomerNotFoundException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void getAllIssues() {

        try {
            this.customerService.registerCustomer(customer);
            this.issueService.addIssueToCustomer(customer.getCustomerId(), issue);
            Assertions.assertNotNull(this.issueService.getAllIssues());
        } catch (CustomerNotFoundException | CustomerRegisterException e) {
            throw new RuntimeException(e);
        } catch (NullIssueException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(7)
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
    @Order(8)
    public void getIssuesByWrongCustomerId() {
        try {
            this.customerService.registerCustomer(customer);
            this.issueService.addIssueToCustomer(customer.getCustomerId(), issue);
            assertThrows(CustomerNotFoundException.class, () -> {
                this.issueService.getAllIssuesByCustomerId(121);
            });

        } catch (CustomerNotFoundException | CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(9)
    public void deleteIssueById() {
        try {
            this.customerService.registerCustomer(customer);
            this.issueService.addIssueToCustomer(customer.getCustomerId(), issue);
            this.issueService.deleteIssueFromCustomer(customer.getCustomerId(), issue.getIssueId());
            assertThrows(NullPointerException.class, () -> {
                this.issueService.updateIssueDescriptionById(customer.getCustomerId(), issue.getIssueId(), "New updated issue description"); // Call the method that should throw the exception
            });
        } catch (IssueNotFoundException | CustomerNotFoundException | CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(10)
    public void deleteIssueByIdWrongCustomerId() {
        try {
            this.customerService.registerCustomer(customer);
            this.issueService.addIssueToCustomer(customer.getCustomerId(), issue);

            assertThrows(CustomerNotFoundException.class, () -> {
                this.issueService.deleteIssueFromCustomer(201, issue.getIssueId());
            });
        } catch (CustomerNotFoundException | CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(11)
    public void deleteIssueByIdWrongIssueId() {
        try {
            this.customerService.registerCustomer(customer);
            this.issueService.addIssueToCustomer(customer.getCustomerId(), issue);

            assertThrows(IssueNotFoundException.class, () -> {
                this.issueService.deleteIssueFromCustomer(customer.getCustomerId(), 178);
            });
        } catch (CustomerNotFoundException | CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(12)
    public void emptyIssueListTest() {
        try{
            this.customerService.registerCustomer(customer);
            this.issueService.addIssueToCustomer(customer.getCustomerId(), issue);
            this.issueService.deleteIssueFromCustomer(customer.getCustomerId(), issue.getIssueId());
            Assertions.assertNotNull(customer.getIssues());
        }
        catch(IssueNotFoundException | CustomerNotFoundException | CustomerRegisterException e){
            Assertions.fail(e.getMessage());
        }
    }


}


