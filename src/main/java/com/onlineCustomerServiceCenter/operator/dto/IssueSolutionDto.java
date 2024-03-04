package com.onlineCustomerServiceCenter.operator.dto;

public class IssueSolutionDto {
    private Integer issueId;
    private String solutionDescription;
    private Integer operatorId;

    public IssueSolutionDto(Integer issueId, String solutionDescription, Integer operatorId) {
        this.issueId = issueId;
        this.solutionDescription = solutionDescription;
        this.operatorId = operatorId;
    }

    public IssueSolutionDto() {
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public String getSolutionDescription() {
        return solutionDescription;
    }

    public void setSolutionDescription(String solutionDescription) {
        this.solutionDescription = solutionDescription;
    }
}
