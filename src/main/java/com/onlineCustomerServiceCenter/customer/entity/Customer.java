package com.onlineCustomerServiceCenter.customer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer customerId;

    @NotNull(message = "customer first name cannot be null")
    @NotBlank(message = "customer first name cannot be blank ")
    private String firstName;
    @NotNull(message = "customer last name cannot be null")
    @NotBlank(message = "customer last name cannot be blank ")
    private String lastName;
    @NotNull(message = "customer email cannot be null")
    @NotBlank(message = "customer email cannot be blank")
    @Email(message="email is not in correct format, Eg. ford@gmail.com")
    private String email;
    @NotNull(message = "customer password cannot be null")
    @NotBlank(message = "customer password cannot be blank")
    @Pattern(regexp = "[A-Za-z\\d@$!%*?&]{8}$", message="password should contain 8 characters")
    private String password;
    @NotNull(message = "customer city cannot be null")
    @NotBlank(message = "customer city cannot be null")
    private String city;
    @NotNull(message = "customer phoneNumber cannot be null")
    @NotBlank(message = "customer name cannot be null")
    @Pattern(regexp="^\\d{10}$", message="phone number should contain 10 digit")
    private String phoneNumber;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Issue> issues = new ArrayList<>();


    public Customer(String firstName, String lastName , String email, String password, String city, String phoneNumber) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.email = email;
        this.password = password;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }
}

