package com.onlineCustomerServiceCenter.issue.service;

import com.onlineCustomerServiceCenter.customer.dao.CustomerRepository;
import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerNotFoundException;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerRegisterException;
import com.onlineCustomerServiceCenter.issue.dao.IssueRepository;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.issue.exception.NullIssueException;
import com.onlineCustomerServiceCenter.issue.service.IssueService;
import com.onlineCustomerServiceCenter.solution.entity.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Override
    public Issue addIssue(Issue issue) throws NullIssueException {
        if(issue == null){
            throw new NullIssueException("Can't create null object");
        }
       return this.issueRepository.save(issue);

    }

    @Override
    public Issue updateIssueDescById(Issue issue, String newDesc) throws NullIssueException{
        if(issue == null){
            throw new NullIssueException("Can't create null object");
        }
        issue.setIssueDescription(newDesc);
        return issue;
    }

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
    public String addSolutionToIssueById(Integer issueId, Solution solution) throws IssueNotFoundException {
        Optional<Issue> optionalIssue = this.issueRepository.findById(issueId);
        if(optionalIssue.isEmpty()){
            throw new IssueNotFoundException("No Issue exists with the given Issue Id: "+issueId);
        }

        this.issueRepository.getReferenceById(issueId).getSolutions().add(solution);

        return "Solution Added Successfully";
    }



    @Override
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
    public Customer addIssueToCustomer(Integer customerId, Issue newIssue) throws CustomerRegisterException {
        Customer customer;
        Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
        if(optCustomer.isPresent()){
            customer = optCustomer.get();
            customer.getIssues().add(newIssue);
            this.issueRepository.save(newIssue);
            this.customerRepository.save(customer);
        }
        else{
            throw new CustomerRegisterException("No user found with the customerId");
        }
        return customer;
    }
}
