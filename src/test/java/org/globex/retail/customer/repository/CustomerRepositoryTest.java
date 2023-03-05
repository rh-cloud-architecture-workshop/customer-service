package org.globex.retail.customer.repository;

import io.quarkus.test.junit.QuarkusTest;
import org.globex.retail.customer.model.entity.Address;
import org.globex.retail.customer.model.entity.Customer;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class CustomerRepositoryTest {

    @Inject
    CustomerRepository customerRepository;

    @Test
    @Transactional
    void testCreateCustomer() {
        Customer customer = buildCustomer();
        customerRepository.persist(customer);
    }

    @Test
    void findCustomerByEmail() {
        Customer customer = buildCustomer();
        String email = "johndoe2@email.com";
        String userId = "jdoe2";
        customer.setEmail(email);
        customer.setUserId(userId);
        Customer found = customerRepository.findByEmail(email);
        assertThat(found, nullValue());
        persistCustomers(List.of(customer));
        found = customerRepository.findByEmail(email);
        assertThat(found, notNullValue());
        assertThat(found.getAddress(), notNullValue());
        assertThat(found.getAddress().getAddress1(), is("1 Some Street"));
    }

    @Test
    void findCustomerByUserId() {
        Customer customer = buildCustomer();
        String email = "johndoe3@email.com";
        String userId = "jdoe3";
        customer.setEmail(email);
        customer.setUserId(userId);
        Customer found = customerRepository.findByUserId(userId);
        assertThat(found, nullValue());
        persistCustomers(List.of(customer));
        found = customerRepository.findByUserId(userId);
        assertThat(found, notNullValue());
        assertThat(found.getAddress(), notNullValue());
        assertThat(found.getAddress().getAddress1(), is("1 Some Street"));
    }

    @Transactional
    void persistCustomers(List<Customer> customers) {
        customers.forEach(customer -> customerRepository.persist(customer));
    }

    private Customer buildCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setUserId("jdoe");
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
}
