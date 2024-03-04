package com.onlineCustomerServiceCenter.issue;

import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.issue.exception.NullIssueException;
import com.onlineCustomerServiceCenter.solution.entity.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService{
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
    public Issue deleteIssueById(Integer id) throws IssueNotFoundException {

        Optional<Issue> optionalIssue = this.issueRepository.findById(id);
        if(optionalIssue.isEmpty()){
            throw new IssueNotFoundException("No Issue exists with the given Issue Id: "+id);
        }

        Issue issue = optionalIssue.get();
        this.issueRepository.delete(issue);
        return issue;
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

        //return this.issueRepository.findAllByType(type);
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
}
