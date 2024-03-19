package com.onlineCustomerServiceCenter.solution.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Solution {
    @Id
    @GeneratedValue()
    private Integer solutionId;
    private String solutionDescription;
    @Column (columnDefinition = "boolean default false")
    private Boolean isSolutionAccepted;
    private Integer operatorId;

    public Solution(String solutionDescription, Integer operatorId) {
        this.solutionDescription = solutionDescription;
        this.operatorId = operatorId;
        this.isSolutionAccepted = false;
    }

    public Solution(String solutionDescription) {
        this.solutionDescription = solutionDescription;
    }

    public Solution() {

    }
}