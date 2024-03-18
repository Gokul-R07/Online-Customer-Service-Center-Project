package com.onlineCustomerServiceCenter.issue.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.onlineCustomerServiceCenter.solution.entity.Solution;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

//import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
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
        this.issueStatus = "pending";
        this.issueDescription = issueDescription;
        this.ticketClose = false;
        this.solutions = null;
    }

}