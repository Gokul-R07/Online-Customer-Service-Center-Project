package com.onlineCustomerServiceCenter.solution.service;

import com.onlineCustomerServiceCenter.issue.dao.IssueRepository;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.solution.dao.SolutionRepository;
import com.onlineCustomerServiceCenter.solution.entity.Solution;
import com.onlineCustomerServiceCenter.solution.exceptions.SolutionException;
import com.onlineCustomerServiceCenter.solution.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private IssueRepository issueRepository;

    @Override
    public Solution createSolution(String solutionDescription) throws SolutionException {
        if(solutionDescription==null ){
            throw new SolutionException("Solution Description cannot be empty");
        } else if (solutionDescription.isBlank()) {
            throw new SolutionException("Solution Description cannot be blank");
        }
        Solution solution=new Solution(solutionDescription);
        return this.solutionRepository.save(solution);
    }

    @Override
    public String acceptSolution(Integer issueId,  Integer  solutionId) throws SolutionException {
        if (solutionId==null){
            throw new SolutionException("Solution Id cannot be null");
        }

        Optional<Solution> foundSolution= this.solutionRepository.findById(solutionId);
        if(foundSolution.isPresent()){
            Solution solution=foundSolution.get();
            solution.setIsSolutionAccepted(true);
            this.solutionRepository.save(solution);
            Optional<Issue> issueOptional=this.issueRepository.findById(issueId);
            if(issueOptional.isPresent()){
                Issue issue=issueOptional.get();
                issue.setTicketClose(true);
            }
            return "Solution Accepted";
        }
        return null;
    }
}
