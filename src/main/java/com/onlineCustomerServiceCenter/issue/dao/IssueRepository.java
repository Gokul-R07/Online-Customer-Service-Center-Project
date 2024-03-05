package com.onlineCustomerServiceCenter.issue.dao;

import com.onlineCustomerServiceCenter.issue.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {

    //public List<Issue> findAllByType(String type);

}
