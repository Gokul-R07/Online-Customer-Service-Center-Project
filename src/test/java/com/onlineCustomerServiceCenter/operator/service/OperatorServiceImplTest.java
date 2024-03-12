package com.onlineCustomerServiceCenter.operator.service;

import com.onlineCustomerServiceCenter.operator.exceptions.AllocatedIssueExp;
import com.onlineCustomerServiceCenter.operator.exceptions.PendingIssueExp;
import com.onlineCustomerServiceCenter.operator.service.OperatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class OperatorServiceImplTest {

  private OperatorServiceImpl service;

    @Test()
    void getAllAllocatedIssue() {

        Assertions.assertThrows(AllocatedIssueExp.class,()->{
            this.service.getAllAllocatedIssue();
        });
    }

    @Test
    void getAllAllocatedIssueCount() {

        Assertions.assertThrows(AllocatedIssueExp.class,()->{
            this.service.getAllAllocatedIssueCount();
        });
    }

    // Test cases.
    @Test
    void getAllPendingIssueByOperatorId() {
    }

    @Test
    void getAllAllocatedIssueByOperatorId() {
    }

    @Test
    void getAllPendingIssue() {
        Assertions.assertThrows(PendingIssueExp.class,()->{
            this.service.getAllPendingIssue();
        });
    }

    @Test
    void getAllPendingIssueCount() {
        Assertions.assertThrows(PendingIssueExp.class,()->{
            this.service.getAllPendingIssueCount();
        });
    }
}