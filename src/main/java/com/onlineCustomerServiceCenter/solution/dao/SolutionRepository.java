package com.onlineCustomerServiceCenter.solution.dao;

import com.onlineCustomerServiceCenter.solution.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionRepository extends JpaRepository<Solution,Integer> {
}
