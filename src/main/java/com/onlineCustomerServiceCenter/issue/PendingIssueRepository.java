package com.onlineCustomerServiceCenter.issue;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingIssueRepository extends JpaRepository<Issue,Integer> {

}
