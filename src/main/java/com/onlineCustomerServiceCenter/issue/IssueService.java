package com.onlineCustomerServiceCenter.issue;

import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.issue.exception.NullIssueException;
import com.onlineCustomerServiceCenter.solution.Solution;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IssueService {
    public Issue addIssue(Issue issue) throws NullIssueException;
    public Issue updateIssueDescById(Issue issue, String newDesc) throws NullIssueException;

    public Issue deleteIssueById(Integer id) throws IssueNotFoundException;
    public Issue getIssueById(Integer id) throws IssueNotFoundException;
    public List<Issue> getAllIssues();

    public List<Issue> getAllIssuesByType(String type);

    public String addSolutionToIssueById(Integer issueId, List<Solution> solution) throws IssueNotFoundException;
}
