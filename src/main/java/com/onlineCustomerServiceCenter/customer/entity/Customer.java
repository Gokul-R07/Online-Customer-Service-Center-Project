package com.onlineCustomerServiceCenter.customer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;


import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer customerId;
    @NotNull(message = "customer name cannot be null")
    @NotBlank(message = "customer name cannot be blank ")
    private String name;

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
    private String phoneNumber;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    private List<Issue> issues = new ArrayList<>();


        public Customer(String name, String email, String password, String city, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }
}

