package com.onlineCustomerServiceCenter.operator;

import com.onlineCustomerServiceCenter.operator.exceptions.IncorrectPasswordException;
import com.onlineCustomerServiceCenter.operator.exceptions.NullException;
import com.onlineCustomerServiceCenter.operator.exceptions.OperatorNotFoundException;
import com.onlineCustomerServiceCenter.operator.service.OperatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class operatorTests {
    @Autowired
    private OperatorService operatorService;

    @Test
    void loginOperatorPositiveTest() {
        try {
            Assertions.assertEquals("Login successful", this.operatorService.loginOperator("g@gmail.com", "gG123@ee"));
        } catch (OperatorNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IncorrectPasswordException e) {
            throw new RuntimeException(e);
        } catch (NullException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void loginOperatorWithIncorrectPassword() {
        Assertions.assertThrows(IncorrectPasswordException.class, () -> {
            this.operatorService.loginOperator("g@gmail.com", "h321@GG");
        });
    }

    @Test
    void loginOperatorWithIncorrectEmail() {
        Assertions.assertThrows(OperatorNotFoundException.class, () -> {
            this.operatorService.loginOperator("a@gmail.com", "hh321@GG");
        });
    }

    @Test
    public void changePasswordPositiveTest() {
        try {
            Assertions.assertEquals("Password changed successfully",this.operatorService.changePassword("g@gmail.com","htw31234","arc12341"));
        } catch (OperatorNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IncorrectPasswordException e) {
            throw new RuntimeException(e);
        } catch (NullException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void changePasswordWithIncorrectOldPassword() {
        Assertions.assertThrows(IncorrectPasswordException.class, () -> {
            this.operatorService.changePassword("g@gmail.com","ac12341","gokul12341");
        });
    }
    @Test
    public void changePasswordWithIncorrectEmailId() {
        Assertions.assertThrows(OperatorNotFoundException.class, () -> {
            this.operatorService.changePassword("g1@gmail.com","arc12341","gokul12341");
        });
    }
}
