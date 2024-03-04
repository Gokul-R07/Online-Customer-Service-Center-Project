package com.onlineCustomerServiceCenter.solution.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Solution {
    @Id
    @GeneratedValue()
    private Integer solutionId;
    private String solutionDescription;
    @Column (columnDefinition = "boolean default false")
    private Boolean isSolutionAccepted;
    private Integer operatorId;

    public Solution(Integer solutionId, String solutionDescription, Boolean isSolutionAccepted, Integer operatorId) {
        this.solutionId = solutionId;
        this.solutionDescription = solutionDescription;
        this.isSolutionAccepted = isSolutionAccepted;
        this.operatorId = operatorId;
    }

    public Solution() {
    }

    public Solution(String solutionDescription, Integer operatorId) {
        this.solutionDescription = solutionDescription;
        this.operatorId = operatorId;
    }

    public Solution(String solutionDescription) {
        this.solutionDescription = solutionDescription;
    }

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public String getSolutionDescription() {
        return solutionDescription;
    }

    public void setSolutionDescription(String solutionDescription) {
        this.solutionDescription = solutionDescription;
    }

    public Boolean getSolutionAccepted() {
        return isSolutionAccepted;
    }

    public void setSolutionAccepted(Boolean solutionAccepted) {
        isSolutionAccepted = solutionAccepted;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
}