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
    @GeneratedValue()
    private Integer operatorId;

    @Column(name = "firstName")
    @NotNull(message = "First Name cannot be null")
    @NotBlank(message = "First Name cannot be blank")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;

    @Column(name = "password")
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "[A-Za-z\\d@$!%*?&]{8,}$")
    private String password;
    @Column(name = "departmentName")
    private String departmentName;

    @Column(name = "phoneNumber")

    @NotNull(message = "Phone Number cannot be null")
    @NotBlank(message = "Phone Number cannot be blank")
    @Pattern(regexp = "^\\d{10}$")
    private String phoneNumber;

    @Column(name = "city")
    private String city;
    @OneToMany
    private List<Issue> customerIssues = new ArrayList<>();


}