package com.onlineCustomerServiceCenter.issue.service;

import com.onlineCustomerServiceCenter.customer.dao.CustomerRepository;
import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerNotFoundException;
import com.onlineCustomerServiceCenter.issue.dao.IssueRepository;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.issue.exception.NullIssueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private IssueRepository issueRepository;





    @Override
    public Issue getIssueById(Integer id)throws IssueNotFoundException {
        Optional<Issue> optionalIssue = this.issueRepository.findById(id);
        if(optionalIssue.isEmpty()){
            throw new IssueNotFoundException("No Issue exists with the given Issue Id: "+id);
        }
        return optionalIssue.get();
    }

    @Override
    public List<Issue> getAllIssues() throws NullIssueException {
        List<Issue> issuesOptioal=this.issueRepository.findAll();
        if(issuesOptioal.isEmpty())
            throw new NullIssueException("No issue exit in the List");

        return issuesOptioal;
    }

    @Override
    public List<Issue> getAllIssuesByType(String type) {

        return null;
    }



    @Override
    public Issue updateIssueDescriptionById(Integer customerId, Integer issueId, String newDesc) throws CustomerNotFoundException, IssueNotFoundException {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        List<Issue> issues = customer.getIssues();

// Find the specific issue to update based on its ID
        if(issues.isEmpty()){
            throw new NullPointerException("No issue in the list");
        }
        Issue issueToUpdate = issues.stream()
                .filter(issue -> issue.getIssueId().equals(issueId))
                .findFirst()
                .orElseThrow(() -> new IssueNotFoundException("Issue not found in the list"));

// Update the issue description
        issueToUpdate.setIssueDescription(newDesc);
        issueToUpdate.setIssueUpdatedDate(LocalDate.now());



// Save the changes to persist the updated issue description
        issueRepository.save(issueToUpdate);
        return issueToUpdate;

    }

    @Override
    public Customer addIssueToCustomer(Integer customerId, Issue newIssue) throws CustomerNotFoundException {
        Customer customer;
        Optional<Customer> optionalCustomer = this.customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()){
            customer = optionalCustomer.get();
            List<Issue> issueList=customer.getIssues();
            issueList.add(newIssue);
            customer.setIssues(issueList);
            this.issueRepository.save(newIssue);
            this.customerRepository.save(customer);

        }
        else{
            throw new CustomerNotFoundException("No user found with the customerId");
        }
        return this.customerRepository.findById(customerId).get();
    }

    @Override
    public List<Issue> getAllIssuesByCustomerId(Integer customerId) throws CustomerNotFoundException {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return customer.getIssues();
        } else {
            throw new CustomerNotFoundException("Customer Not found within the given Id: " + customerId);
        }
    }

    @Override
    public Issue deleteIssueFromCustomer(Integer customerId, Integer issueId) throws CustomerNotFoundException, IssueNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        Optional<Issue> issueOptional = customer.getIssues().stream()
                .filter(issue -> issue.getIssueId().equals(issueId))
                .findFirst();

        if (issueOptional.isPresent()) {
            Issue deletedIssue = issueOptional.get();
            customer.getIssues().remove(deletedIssue);
            issueRepository.deleteById(issueId);

            customerRepository.save(customer);

            return deletedIssue;
        } else {
            throw new IssueNotFoundException("Issue not found with given id: " + issueId);
        }
    }

    @Override
    public Issue getIssueByIssueId(Integer issueId) throws IssueNotFoundException {
        Optional<Issue> issueOptional = issueRepository.findById(issueId);

        if (issueOptional.isPresent()) {
            return issueOptional.get();
        } else {
            throw new IssueNotFoundException("Issue not found with given id: " + issueId);
        }
    }


}
