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
public class CustomerTest {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;
    Customer customer;

    @BeforeEach
    public void createCustomer(){
        customer=new Customer("karthick", "spk@gmail.com", "gf@13412", "trichy", "9756456878");
    }
    @AfterEach
    public void deleteCustomer(){
        customer=null;
        customerRepository.deleteAll();
    }


    @Test
    @Order(1)
    public void createNewCustomer() {
        try {
            Assertions.assertNotNull(this.customerService.registerCustomer(customer));
        } catch (CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(2)
    public void loginCustomerNotNull() {
        try {
           // Customer newCustomer =new Customer("jai", "jai@gmail.com", "j9@13412", "cbe", "9796656988");
            Customer customer1=this.customerService.registerCustomer(customer);
            Assertions.assertNotNull(this.customerService.loginCustomer(customer1.getEmail(),customer1.getPassword()));
        } catch (CustomerLoginException| CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void registerCustomerWithNewEmail() {
        try {
            this.customerService.registerCustomer(customer);
            this.customerService.registerCustomer(new Customer("karthick", "spk@gmail.com", "gf@13412", "trichy", "9756456878"));
        } catch (CustomerRegisterException e) {
            Assertions.assertEquals("Email already registered, please re try."+customer.getEmail(), e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void UpdateCustomer() {
        try {
            this.customerService.registerCustomer(customer);
            Assertions.assertNotNull(this.customerService.updateCustomer(customer));
        } catch (CustomerUpdateException | CustomerRegisterException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @Order(5)
    public void getAllCustomers(){

        try {
            this.customerService.registerCustomer(customer);
            Assertions.assertNotNull(this.customerService.getAllCustomers());
        } catch (CustomerNotFoundException| CustomerRegisterException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @Order(6)
    public void getCustomerByEmail(){
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
    public void deleteCustomerByEmail(){
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
        Customer newCustomer =new Customer("ravi", "ravi@gmail.com", "ravi4812", "cbe", "9796656988");
        boolean isValidValidEmail =(newCustomer.getEmail()).matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Assertions.assertTrue(isValidValidEmail, "Valid email format ");


    }
    @Test
    @Order(9)
    public void InvalidEmailFormatTest(){
        Customer newCustomer =new Customer("ravi", "ravigmail.com", "ravi4812", "cbe", "9796656988");

        boolean isValidEmail = (newCustomer.getEmail()).matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Assertions.assertFalse(isValidEmail, "Invalid email format");
    }

    @Test
    @Order(10)
    public void ValidPasswordTest(){
        Customer newCustomer =new Customer("ravi", "ravi@gmail.com", "ravi3412", "cbe", "9796656988");
        boolean isValidPassword = (newCustomer.getPassword()).matches("[A-Za-z\\d@$!%*?&]{8}$");
    Assertions.assertTrue(isValidPassword,"Valid password format");
    }

    @Test
    @Order(11)
    public void InvalidPasswordTest(){
        Customer newCustomer =new Customer("jiva", "jiv@gmail.com", "jiva3418962", "cbe", "9796656988");
        boolean isValidPassword = (newCustomer.getPassword()).matches("[A-Za-z\\d@$!%*?&]{8}$");
        Assertions.assertFalse(isValidPassword,"Invalid password format");
    }
    @Test
    @Order(12)
    public void negativeDeleteCustomerTest(){
        Assertions.assertThrows(CustomerDeleteException.class,()->this.customerService.deleteCustomerByEmail("jdd@gmail.com"));
    }
    @Test
    @Order(13)
    public void negativeGetCustomerByEmailTest(){
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
    public void negativeGetAllCustomers(){
        Assertions.assertThrows(CustomerNotFoundException.class, ()->customerService.getAllCustomers());
    }
    @Test
    @Order(15)
    public void negativeLoginCustomerNotNull() {
            Assertions.assertThrows(CustomerLoginException.class,()->customerService.loginCustomer("hi@gmail.com","gfgw@32"));
    }
    @Test
    @Order(16)
    public void negativeUpdateCustomer() {
        Assertions.assertThrows(CustomerUpdateException.class,()->customerService.updateCustomer(customer));
    }



}




