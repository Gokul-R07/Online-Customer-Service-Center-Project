package com.onlineCustomerServiceCenter.operator.dao;

import com.onlineCustomerServiceCenter.operator.dto.OperatorLoginDto;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatorRespository extends JpaRepository<Operator,Integer> {
    Optional<Operator> findOperatorByEmail(String email);
}
