package com.onlineCustomerServiceCenter.issue.entity;


import com.onlineCustomerServiceCenter.solution.entity.Solution;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

//import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//@Data
@Entity
public class Issue {

    @Id
    @GeneratedValue()
    private Integer issueId;
    private String issueType;
    private LocalDate issueCreationDate;
    private LocalDate issueUpdatedDate;

    private Boolean ticketClose;

    private String issueStatus;

    @Size(min = 10, max = 50, message = "The Issue Description must be between 10 and 50 characters")
    private String issueDescription;

    @OneToMany
    private List<Solution> solutions=new ArrayList<>();

    public Issue() {
    }

    public Issue(String issueType, String issueDescription) {
        this.issueType = issueType;
        this.issueCreationDate = LocalDate.now();
        this.issueUpdatedDate = null;
        this.issueStatus = "Unsolved";
        this.issueDescription = issueDescription;
        this.ticketClose = false;
        this.solutions = null;
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
        return issueCreationDate;
    }

    public void setIssueCreationDate(LocalDate issueCreationDate) {
        this.issueCreationDate = issueCreationDate;
    }

    public LocalDate getIssueUpdatedDate() {
        return issueUpdatedDate;
    }

    public void setIssueUpdatedDate(LocalDate issueUpdatedDate) {
        this.issueUpdatedDate = issueUpdatedDate;
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
                ", IssueCreationDate=" + issueCreationDate +
                ", IssueUpdatedDate=" + issueUpdatedDate +
                ", issueStatus='" + issueStatus + '\'' +
                ", issueDescription='" + issueDescription + '\'' +
                ", solutions=" + solutions +
                '}';
    }

    public Boolean getTicketClose() {
        return ticketClose;
    }

    public void setTicketClose(Boolean ticketClose) {
        this.ticketClose = ticketClose;
    }


}