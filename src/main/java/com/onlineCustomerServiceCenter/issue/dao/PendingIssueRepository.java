package com.onlineCustomerServiceCenter.issue.dao;

import com.onlineCustomerServiceCenter.issue.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingIssueRepository extends JpaRepository<Issue,Integer> {

}
