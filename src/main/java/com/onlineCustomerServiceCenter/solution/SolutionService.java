package com.onlineCustomerServiceCenter.solution;

import com.onlineCustomerServiceCenter.solution.entity.Solution;
import com.onlineCustomerServiceCenter.solution.exceptions.SolutionException;

public interface SolutionService {

   Solution createSolution(String solutionDescription) throws SolutionException;
   Solution acceptSolution(Integer  solutionId) throws SolutionException;
}
