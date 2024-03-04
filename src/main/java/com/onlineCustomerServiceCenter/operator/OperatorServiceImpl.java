package com.onlineCustomerServiceCenter.operator;

import com.onlineCustomerServiceCenter.issue.Issue;
import com.onlineCustomerServiceCenter.issue.IssueRepository;
import com.onlineCustomerServiceCenter.issue.IssueService;
import com.onlineCustomerServiceCenter.solution.Solution;
import com.onlineCustomerServiceCenter.solution.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorRespository operatorRespository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private SolutionService solutionService;
    @Autowired
    private IssueService issueService;



    @Override
    public String loginOperator(String email, String password) {

        Optional<OperatorLoginDto> foundOperator= this.operatorRespository.findOperatorByEmail(email);
        if(foundOperator.isPresent()){
                if(foundOperator.get().getPassword().equals(password)){
                    return "Login Successfull";
                }else{
                    return "Password is Wrong";
                }

        }else {
            return "Account does not exits";
        }
    }



    @Override
    public Operator updateOperatorProfile(Operator updatedoperator) {
        return this.operatorRespository.save(updatedoperator);
    }
    @Override
    public Issue addIssueSolution(String issueId, String solutionDescription) {
      Optional<Issue> issueOptional=  this.issueRepository.findById(Integer.parseInt(issueId));
      if(issueOptional.isPresent()){
          Issue issue=issueOptional.get();

//        Solution solution= solutionService.createSolution(solutionDescription);
//          this.
//          issue.getSolutions().add();

//         Solution solution= solutionService.createSolution(solutionDescription);
//        issueService.addSolutionToIssue(issueId,solution);
//        return this.issueService.getIssueById(issueId);

      }
        return null;
    }
}
