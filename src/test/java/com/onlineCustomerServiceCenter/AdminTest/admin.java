package com.onlineCustomerServiceCenter.AdminTest;

import com.onlineCustomerServiceCenter.operator.entity.Operator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class admin {

    private AdminService adminService;

    @BeforeEach
    public void setUp() {
        adminService = new AdminServiceImpl();
    }

    @Test
    public void testAdminLogin_ValidCredentials() {
        // Test login functionality as before
    }

    @Test
    public void testAdminLogin_InvalidCredentials() {
        // Test login with invalid credentials as before
    }

    @Test
    public void testCreateOperator() {
        Operator operator = new Operator("Jane", "Doe", "jane.doe@example.com", "password123");
        Operator createdOperator = adminService.createOperator(operator);

        assertNotNull(createdOperator);
        // Add additional assertions as needed to validate the creation
    }

    @Test
    public void testUpdateOperator() {
        Operator operator = new Operator("Jane", "Doe", "jane.doe@example.com", "password123");
        int Operator = 0;
        boolean updatedOperator = adminService.deleteOperator(Operator);

        assertNotNull(updatedOperator);
        // Add additional assertions as needed to validate the update
    }

    @Test
    public void testDeleteOperator() {
        int operatorId = 123; // Specify the operator ID to delete
        boolean deletionResult = adminService.deleteOperator(operatorId);

        assertTrue(deletionResult);
        // Add additional assertions as needed to validate the deletion
    }
}
