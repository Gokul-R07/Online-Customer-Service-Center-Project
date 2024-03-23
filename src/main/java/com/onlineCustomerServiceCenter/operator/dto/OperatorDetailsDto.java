package com.onlineCustomerServiceCenter.operator.dto;

import lombok.Data;

@Data
public class OperatorDetailsDto {
    Integer operatorId;
    String firstName;
    String lastName;
    Integer allocatedIssueCount;
    Integer pendingIssueCount;
}
