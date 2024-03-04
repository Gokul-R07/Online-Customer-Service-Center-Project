package com.onlineCustomerServiceCenter.operator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class PredefinedSolution {

    @Id
    @GeneratedValue()
    private Integer predefinedSolutionId;
    private String predefinedSolutionType;
    private String predefinesSolutionMessage;

    public PredefinedSolution(Integer predefinedSolutionId, String predefinedSolutionType, String predefinesSolutionMessage) {
        this.predefinedSolutionId = predefinedSolutionId;
        this.predefinedSolutionType = predefinedSolutionType;
        this.predefinesSolutionMessage = predefinesSolutionMessage;
    }

    public PredefinedSolution() {
    }

    public Integer getPredefinedSolutionId() {
        return predefinedSolutionId;
    }

    public void setPredefinedSolutionId(Integer predefinedSolutionId) {
        this.predefinedSolutionId = predefinedSolutionId;
    }

    public String getPredefinedSolutionType() {
        return predefinedSolutionType;
    }

    public void setPredefinedSolutionType(String predefinedSolutionType) {
        this.predefinedSolutionType = predefinedSolutionType;
    }

    public String getPredefinesSolutionMessage() {
        return predefinesSolutionMessage;
    }

    public void setPredefinesSolutionMessage(String predefinesSolutionMessage) {
        this.predefinesSolutionMessage = predefinesSolutionMessage;
    }
}