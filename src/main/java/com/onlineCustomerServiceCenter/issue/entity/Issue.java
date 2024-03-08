package com.onlineCustomerServiceCenter.issue.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.onlineCustomerServiceCenter.solution.entity.Solution;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.*;

//import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//@Data
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {

    @Id
    @GeneratedValue()
    private Integer issueId;
    private String issueType;
    private LocalDate issueCreationDate;
    private LocalDate issueUpdatedDate;
    private Boolean ticketClose = false;

    private String issueStatus;

    @Size(min = 10, max = 50, message = "The Issue Description must be between 10 and 50 characters")
    private String issueDescription;

    @OneToMany
    private List<Solution> solutions=new ArrayList<>();

}