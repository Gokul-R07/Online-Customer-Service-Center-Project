package com.onlineCustomerServiceCenter.issue;


import com.onlineCustomerServiceCenter.solution.entity.Solution;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Issue {

    @Id
    @GeneratedValue()
    private Integer issueId;
    private String issueType;
    private LocalDate IssueCreationDate;
    private LocalDate IssueUpdatedDate;

    private String issueStatus;

    @Size(min = 10, max = 50, message = "The Issue Description must be between 10 and 50 characters")
    private String issueDescription;

    @OneToMany
    private List<Solution> solutions=new ArrayList<>();

    public Issue() {
    }

    public Issue(Integer issueId, String issueType, LocalDate issueCreationDate, LocalDate issueUpdatedDate, String issueStatus, String issueDescription, List<Solution> solutions) {
        this.issueId = issueId;
        this.issueType = issueType;
        IssueCreationDate = issueCreationDate;
        IssueUpdatedDate = issueUpdatedDate;
        this.issueStatus = issueStatus;
        this.issueDescription = issueDescription;
        this.solutions = solutions;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public LocalDate getIssueCreationDate() {
        return IssueCreationDate;
    }

    public void setIssueCreationDate(LocalDate issueCreationDate) {
        IssueCreationDate = issueCreationDate;
    }

    public LocalDate getIssueUpdatedDate() {
        return IssueUpdatedDate;
    }

    public void setIssueUpdatedDate(LocalDate issueUpdatedDate) {
        IssueUpdatedDate = issueUpdatedDate;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "issueId=" + issueId +
                ", issueType='" + issueType + '\'' +
                ", IssueCreationDate=" + IssueCreationDate +
                ", IssueUpdatedDate=" + IssueUpdatedDate +
                ", issueStatus='" + issueStatus + '\'' +
                ", issueDescription='" + issueDescription + '\'' +
                ", solutions=" + solutions +
                '}';
    }
}