package com.onlineCustomerServiceCenter.issue.service;

import com.onlineCustomerServiceCenter.customer.dao.CustomerRepository;
import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerNotFoundException;
import com.onlineCustomerServiceCenter.issue.dao.IssueRepository;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.solution.entity.Solution;
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
    public Customer deleteIssueById(Integer customerId,Integer id) throws IssueNotFoundException {
        Optional<Customer> customerid=this.customerRepository.findById(customerId);
        Optional<Issue> optionalIssue = this.issueRepository.findById(id);
        if(optionalIssue.isEmpty()){
            throw new IssueNotFoundException("No Issue exists with the given Issue Id: "+id);
        }
        if(customerid.isEmpty()){
            throw new IssueNotFoundException("No Issue exists with the given Issue Id: "+id);
        }
        Customer customer=customerid.get();
        Issue issue=optionalIssue.get();
        List<Issue>issueList=customer.getIssues();
        issueList.remove(issue);
        this.issueRepository.deleteById(issue.getIssueId());
        return this.customerRepository.save(customer);

    }



    @Override
    public Issue getIssueById(Integer id)throws IssueNotFoundException {
        Optional<Issue> optionalIssue = this.issueRepository.findById(id);
        if(optionalIssue.isEmpty()){
            throw new IssueNotFoundException("No Issue exists with the given Issue Id: "+id);
        }
        return optionalIssue.get();
    }

    @Override
    public List<Issue> getAllIssues() {
        return this.issueRepository.findAll();
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


    public Customer updateIssueDescById(Integer customerId, Integer issueId, Issue issue) throws CustomerRegisterException, IssueNotFoundException {
        Customer customer;
        Optional<Issue> optIssue = issueRepository.findById(issueId);
        Optional<Customer> optCustomer = customerRepository.findById(customerId);
        if(optIssue.isPresent()){
            if(optCustomer.isPresent()){

                 customer = optCustomer.get();
                 issue.setIssueId(issueId);
                this.issueRepository.save(issue);

            }
            else{
                throw new CustomerRegisterException("Customer not found");
            }
        }
        else{
            throw new IssueNotFoundException("Issue not found");
        }
        return customer;

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

        Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
        if(optCustomer.isPresent()){
            customer = optCustomer.get();
            customer.getIssues().add(newIssue);

            this.issueRepository.save(newIssue);
            this.customerRepository.save(customer);
        }
        else{
            throw new CustomerNotFoundException("No user found with the customerId");
        }
        return customer;
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
    public String deleteIssueFromCustomer(Integer customerId, Integer issueId) throws CustomerNotFoundException, IssueNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        boolean isRemoved = customer.getIssues().removeIf(issue -> issue.getIssueId().equals(issueId));
        if (!isRemoved) {
            throw new IssueNotFoundException("Issue not found with given id: " + issueId);
        } else {
            customerRepository.save(customer);
            issueRepository.deleteById(issueId);
            return "Deletion success";
        }
    }

}
