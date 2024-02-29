package com.onlineCustomerServiceCenter.customer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlineCustomerServiceCenter.issue.Issue;
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

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class Customer {
    @Id
    @GeneratedValue()
    private Integer customerId;
    @NotNull(message = "customer name cannot be null")
    @NotBlank(message = "customer name cannot be blank ")
    private String name;

    @NotNull(message = "customer email cannot be null")
    @NotBlank(message = "customer email cannot be blank")
    @Email
    private String email;
    @NotNull(message = "customer password cannot be null")
    @NotBlank(message = "customer password cannot be blank")
    @Pattern(regexp = "[A-Za-z\\d@$!%*?&]{8}$")
    private String password;
    @NotNull(message = "customer city cannot be null")
    @NotBlank(message = "customer city cannot be null")
    private String city;
    @NotNull(message = "customer phoneNumber cannot be null")
    @NotBlank(message = "customer name cannot be null")
    @Pattern(regexp = "^\\d{10}$")
    private String phoneNumber;
    @JsonIgnore
    @OneToMany
    private List<Issue> issues = new ArrayList<>();


}
