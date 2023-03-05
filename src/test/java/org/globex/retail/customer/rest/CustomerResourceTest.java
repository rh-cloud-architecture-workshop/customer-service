package org.globex.retail.customer.rest;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.globex.retail.customer.model.entity.Address;
import org.globex.retail.customer.model.entity.Customer;
import org.globex.retail.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.blankOrNullString;

@QuarkusTest
public class CustomerResourceTest {

    @Inject
    CustomerRepository customerRepository;

    @Test
    void testGetCustomerByUserId() {
        String userId = "jdoe-resourcetest";
        Customer customer = buildCustomer(userId);
        persistCustomers(List.of(customer));

        RestAssured.given()
                .when().get("/public/customer/id/jdoe-resourcetest")
                .then()
                .statusCode(200)
                .body("userId", is(userId),
                        "firstName", is(customer.getFirstName()),
                        "address", notNullValue(),
                        "address.address1", is(customer.getAddress().getAddress1()));
    }

    @Test
    void testGetCustomerByUserId_WhenUserIdDoesNotExist() {
        String userId = "jdoe-resourcetest-doesnotexist";

        RestAssured.given()
                .when().get("/public/customer/id/" + userId)
                .then()
                .statusCode(404)
                .body(blankOrNullString());
    }

    private Customer buildCustomer(String userId) {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setUserId(userId);
        customer.setEmail("jdoe@email.com");
        customer.setPhone("111 222 332 444");
        Address address = new Address();
        address.setAddress1("1 Some Street");
        address.setAddress2("");
        address.setCity("New York");
        address.setZipCode("12345");
        address.setState("NY");
        address.setCountry("USA");
        customer.setAddress(address);
        address.setCustomer(customer);
        return customer;
    }

    @Transactional
    void persistCustomers(List<Customer> customers) {
        customers.forEach(customer -> customerRepository.persist(customer));
    }

}
