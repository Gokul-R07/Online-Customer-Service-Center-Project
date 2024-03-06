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
            Assertions.assertEquals("Login successful",this.operatorService.loginOperator("g@gmail.com","hh321@GG"));
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

//    @Test
//    void changePasswordPositiveTest(){
//        try {
//            this.operatorService.changePassword("g@gmail.com","hh321@GG","a8Good@");
//        } catch (OperatorNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IncorrectPasswordException e) {
//            throw new RuntimeException(e);
//        } catch (NullException e) {
//            throw new RuntimeException(e);
//        }
//    }


}
