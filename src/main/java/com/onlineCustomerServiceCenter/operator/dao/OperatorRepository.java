package com.onlineCustomerServiceCenter.operator.dao;

import com.onlineCustomerServiceCenter.operator.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatorRepository extends JpaRepository<Operator,Integer> {
    Optional<Operator> findOperatorByEmail(String email);
}
