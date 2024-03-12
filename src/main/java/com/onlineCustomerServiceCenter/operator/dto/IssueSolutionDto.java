package com.onlineCustomerServiceCenter.operator.dto;

public class IssueSolutionDto {
    private Integer issueId;
    private String solutionDescription;

    public IssueSolutionDto() {
    }

    public IssueSolutionDto(Integer issueId, String solutionDescription) {
        this.issueId = issueId;
        this.solutionDescription = solutionDescription;
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
