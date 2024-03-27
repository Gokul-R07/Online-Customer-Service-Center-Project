package com.onlineCustomerServiceCenter.Customer;
import com.onlineCustomerServiceCenter.customer.dao.CustomerRepository;
import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.*;
import com.onlineCustomerServiceCenter.customer.service.CustomerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CustomerTest {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;
    Customer customer;

    @BeforeEach
    void registerCustomer(){
        customer=new Customer("karthick","S P", "spk@gmail.com", "gf@13412", "trichy", "9756456878");
    }
    @AfterEach
    public void deleteCustomer(){
        customer=null;
        customerRepository.deleteAll();
    }


    @Test
    @Order(1)
    void when_registerNewCustomer_is_called_return_customer_object() {
        try {
            Assertions.assertNotNull(this.customerService.registerCustomer(customer));
        } catch (CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(2)
    void when_loginCustomer_is_called_with_notNull_email_notNull_password_return_customer_object() {
        try {
            Customer customer1=this.customerService.registerCustomer(customer);
            Assertions.assertNotNull(this.customerService.loginCustomer(customer1.getEmail(),customer1.getPassword()));
        } catch (CustomerLoginException| CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(3)
    void when_registerCustomer_is_called_with_newEmail_return_customer_object() {
        try {
            this.customerService.registerCustomer(customer);
            this.customerService.registerCustomer(new Customer("karthick", "S P","spk@gmail.com", "gf@13412", "trichy", "9756456878"));
        } catch (CustomerRegisterException e) {
            Assertions.assertEquals("Email already registered, please re try."+customer.getEmail(), e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void when_UpdateCustomer_is_called_withExistingCustomerAccount_return_customer_object() {
        try {
            this.customerService.registerCustomer(customer);
            Assertions.assertNotNull(this.customerService.updateCustomer(customer));
        } catch (CustomerUpdateException | CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(5)
    void when_getAllCustomers_is_called_withExistingCustomerAccount_return_customer_object(){

        try {
            this.customerService.registerCustomer(customer);
            Assertions.assertNotNull(this.customerService.getAllCustomers());
        } catch (CustomerNotFoundException| CustomerRegisterException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @Order(6)
    void when_getCustomerByEmail_is_called_withRegisteredEmail_return_customer_object(){
        try{
            this.customerService.registerCustomer(customer);
            Assertions.assertNotNull(this.customerService.getCustomerByEmail(customer.getEmail()));
        }
        catch(CustomerNotFoundException | CustomerRegisterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void when_deleteCustomerByEmail_is_called_withRegisteredEmail_return_customer_object(){
        try {
            this.customerService.registerCustomer(customer);
            Assertions.assertNotNull(this.customerService.deleteCustomerByEmail(customer.getEmail()));
        } catch (CustomerDeleteException |CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @Order(8)
    public void validEmailFormatTest()  {
        Customer newCustomer =new Customer("ravi", "S P","ravi@gmail.com", "ravi4812", "cbe", "9796656988");
        boolean isValidValidEmail =(newCustomer.getEmail()).matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Assertions.assertTrue(isValidValidEmail, "Valid email format ");


    }
    @Test
    @Order(9)
    public void InvalidEmailFormatTest(){
        Customer newCustomer =new Customer("ravi","S P", "ravigmail.com", "ravi4812", "cbe", "9796656988");

        boolean isValidEmail = (newCustomer.getEmail()).matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Assertions.assertFalse(isValidEmail, "Invalid email format");
    }

    @Test
    @Order(10)
    public void ValidPasswordTest(){
        Customer newCustomer =new Customer("ravi","S P", "ravi@gmail.com", "ravi3412", "cbe", "9796656988");
        boolean isValidPassword = (newCustomer.getPassword()).matches("[A-Za-z\\d@$!%*?&]{8}$");
        Assertions.assertTrue(isValidPassword,"Valid password format");
    }

    @Test
    @Order(11)
    public void InvalidPasswordTest(){
        Customer newCustomer =new Customer("jiva","S P", "jiv@gmail.com", "jiva3418962", "cbe", "9796656988");
        boolean isValidPassword = (newCustomer.getPassword()).matches("[A-Za-z\\d@$!%*?&]{8}$");
        Assertions.assertFalse(isValidPassword,"Invalid password format");
    }
    @Test
    @Order(12)
    void when_DeleteCustomer_is_called_with_unregisteredEmail_throw_CustomerDeletionException(){
        Assertions.assertThrows(CustomerDeleteException.class,()->this.customerService.deleteCustomerByEmail("jdd@gmail.com"));
    }
    @Test
    @Order(13)
    void when_GetCustomerByEmail_is_called_with_unregisteredEmail_throw_CustomerNotFoundException(){
        try{
            this.customerService.registerCustomer(customer);
            Assertions.assertThrows(CustomerNotFoundException.class, ()->customerService.getCustomerByEmail("hi@gmail.com"));
        }
        catch(CustomerRegisterException e){
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @Order(14)
    public void when_GetAllCustomers_is_called_with_unregisteredEmail_throw_CustomerNotFoundException(){
        Assertions.assertThrows(CustomerNotFoundException.class, ()->customerService.getAllCustomers());
    }
    @Test
    @Order(15)
    public void when_LoginCustomer_is_called_with_unregisteredEmail_throw_CustomerLoginException() {
        Assertions.assertThrows(CustomerLoginException.class,()->customerService.loginCustomer("hi@gmail.com","gfgw@32"));
    }
    @Test
    @Order(16)
    public void when_UpdateCustomer_is_called_with_notExistingCustomerAccount_throw_CustomerUpdateException() {
        Assertions.assertThrows(CustomerUpdateException.class,()->customerService.updateCustomer(customer));
    }
    @Test
    @Order(17)
    void ValidPhoneNumberTest(){
        boolean isValidPhoneNumber= (customer.getPhoneNumber()).matches("^\\d{10}$");
        Assertions.assertTrue(isValidPhoneNumber,"Valid password format");
    }
    @Test
    @Order(18)
    void InvalidPhoneNumberTest(){
        Customer newCustomer =new Customer("jiva", "k","jiv@gmail.com", "jiva3418962", "cbe", "9799096656988");

        boolean isValidPhoneNumber= (newCustomer.getPhoneNumber()).matches("^\\d{10}$");
        Assertions.assertFalse(isValidPhoneNumber,"Invalid phone format");
    }



}




