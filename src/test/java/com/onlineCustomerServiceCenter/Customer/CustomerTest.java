package com.onlineCustomerServiceCenter.Customer;
import com.onlineCustomerServiceCenter.customer.dao.CustomerRepository;
import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.*;
import com.onlineCustomerServiceCenter.customer.service.CustomerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
 class CustomerTest {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;
    Customer customer;

    @BeforeEach
     void createCustomer(){
        customer=new Customer("karthick", "spk@gmail.com", "gf@13412", "trichy", "9756456878");
    }
    @AfterEach
    void deleteCustomerTest(){
        customer=null;
        customerRepository.deleteAll();
    }


    @Test
    @Order(1)
    void createNewCustomerTest() {
        try {
            Assertions.assertNotNull(this.customerService.registerCustomer(customer));
        } catch (CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(2)
    void loginCustomerNotNullTest() {
        try {

            Customer customer1=this.customerService.registerCustomer(customer);
            Assertions.assertNotNull(this.customerService.loginCustomer(customer1.getEmail(),customer1.getPassword()));
        } catch (CustomerLoginException| CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(3)
    void registerCustomerWithNewEmailTest() {
        try {
            this.customerService.registerCustomer(customer);
            this.customerService.registerCustomer(new Customer("karthick", "sk@gmail.com", "gf@13412", "trichy", "9756456878"));
        } catch (CustomerRegisterException e) {
            Assertions.assertEquals("Email already registered, please re try."+customer.getEmail(), e.getMessage());
        }
    }

    @Test
    @Order(4)
    void UpdateCustomerTest() {
        try {
            this.customerService.registerCustomer(customer);
            Assertions.assertNotNull(this.customerService.updateCustomer(customer));
        } catch (CustomerUpdateException | CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @Order(5)
    void getAllCustomersTest(){

        try {
            this.customerService.registerCustomer(customer);
            Assertions.assertNotNull(this.customerService.getAllCustomers());
        } catch (CustomerNotFoundException| CustomerRegisterException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @Order(6)
    void getCustomerByIdTest(){
        try{
            this.customerService.registerCustomer(customer);
            Assertions.assertNotNull(this.customerService.getCustomerById(customer.getCustomerId()));
        }
        catch(CustomerNotFoundException | CustomerRegisterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(7)
    void deleteCustomerByEmailTest(){
        try {
            this.customerService.registerCustomer(customer);
            Assertions.assertNotNull(this.customerService.deleteCustomerById(customer.getCustomerId()));
        } catch (CustomerDeleteException |CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @Order(8)
    void validEmailFormatTest()  {
        Customer newCustomer =new Customer("ravi", "ravi@gmail.com", "ravi4812", "cbe", "9796656988");
        boolean isValidValidEmail =(newCustomer.getEmail()).matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Assertions.assertTrue(isValidValidEmail, "Valid email format ");


    }
    @Test
    @Order(9)
    void InvalidEmailFormatTest(){
        Customer newCustomer =new Customer("ravi", "ravigmail.com", "ravi4812", "cbe", "9796656988");

        boolean isValidEmail = (newCustomer.getEmail()).matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Assertions.assertFalse(isValidEmail, "Invalid email format");
    }

    @Test
    @Order(10)
    void ValidPasswordTest(){
        Customer newCustomer =new Customer("ravi", "ravi@gmail.com", "ravi3412", "cbe", "9796656988");
        boolean isValidPassword = (newCustomer.getPassword()).matches("[A-Za-z\\d@$!%*?&]{8}$");
    Assertions.assertTrue(isValidPassword,"Valid password format");
    }

    @Test
    @Order(11)
    void InvalidPasswordTest(){
        Customer newCustomer =new Customer("jiva", "jiv@gmail.com", "jiva3418962", "cbe", "9796656988");
        boolean isValidPassword = (newCustomer.getPassword()).matches("[A-Za-z\\d@$!%*?&]{8}$");
        Assertions.assertFalse(isValidPassword,"Invalid password format");
    }
    @Test
    @Order(12)
     void negativeDeleteCustomerTest(){
        Assertions.assertThrows(CustomerDeleteException.class,()->this.customerService.deleteCustomerById(12));
    }
    @Test
    @Order(13)
    void negativeGetCustomerByEmailTest(){
        try{
            this.customerService.registerCustomer(customer);
            Assertions.assertThrows(CustomerNotFoundException.class, ()->customerService.getCustomerById(11));
        }
        catch(CustomerRegisterException e){
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @Order(14)
    void negativeGetAllCustomers(){
        Assertions.assertThrows(CustomerNotFoundException.class, ()->customerService.getAllCustomers());
    }
    @Test
    @Order(15)
    void negativeLoginCustomerNotNull() {
            Assertions.assertThrows(CustomerLoginException.class,()->customerService.loginCustomer("hi@gmail.com","gfgw@32"));
    }
    @Test
    @Order(16)
    void negativeUpdateCustomerTest() {
        Assertions.assertThrows(CustomerUpdateException.class,()->customerService.updateCustomer(customer));
    }
    @Test
    @Order(17)
     void positivePhoneNumberTest(){
        boolean isValidPassword = (customer.getPhoneNumber()).matches("^\\d{10}$");
        Assertions.assertTrue(isValidPassword,"Valid password format");
    }
    @Test
    @Order(18)
     void negativePhoneNumberTest(){
        Customer newCustomer =new Customer("jiva", "jiv@gmail.com", "jiva3418962", "cbe", "9799096656988");

        boolean isValidPassword = (newCustomer.getPhoneNumber()).matches("^\\d{10}$");
        Assertions.assertFalse(isValidPassword,"Invalid password format");
    }

}




