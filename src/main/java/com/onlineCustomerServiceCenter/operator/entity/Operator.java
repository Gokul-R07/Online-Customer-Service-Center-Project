package com.onlineCustomerServiceCenter.operator.entity;

import com.onlineCustomerServiceCenter.issue.entity.Issue;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Operator {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer operatorId;


    @NotNull(message = "First Name cannot be null")
    @NotBlank(message = "First Name cannot be blank")
    private String firstName;

    private String lastName;
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp="[A-Za-z\\d@$!%*?&]{8,}$")
    private String password;
    private String departmentName;

    @NotNull(message = "Phone Number cannot be null")
    @NotBlank(message = "Phone Number cannot be blank")
    @Pattern(regexp="^\\d{10}$")
    private String phoneNumber;
    private String city;
    @OneToMany
    private List<Issue> customerIssues=new ArrayList<>();






}