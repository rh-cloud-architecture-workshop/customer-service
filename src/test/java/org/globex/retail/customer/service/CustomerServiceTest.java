package org.globex.retail.customer.service;

import io.quarkus.test.junit.QuarkusTest;
import org.globex.retail.customer.model.dto.CustomerDto;
import org.globex.retail.customer.model.entity.Address;
import org.globex.retail.customer.model.entity.Customer;
import org.globex.retail.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class CustomerServiceTest {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    CustomerService customerService;

    @Test
    void testGetCustomerByCustomerId() {
        String userId = "jdoe-servicetest";
        Customer customer = buildCustomer(userId);
        persistCustomers(List.of(customer));
        CustomerDto customerDto = customerService.getCustomerByCustomerId(userId);
        assertThat(customerDto, notNullValue());
        assertThat(customerDto.getUserId(), is(userId));
        assertThat(customerDto.getFirstName(), is(customer.getFirstName()));
        assertThat(customerDto.getLastName(), is(customer.getLastName()));
        assertThat(customerDto.getEmail(), is(customer.getEmail()));
        assertThat(customerDto.getPhone(), is(customer.getPhone()));
        assertThat(customerDto.getAddress(), notNullValue());
        assertThat(customerDto.getAddress().getAddress1(), is(customer.getAddress().getAddress1()));
        assertThat(customerDto.getAddress().getAddress2(), is(customer.getAddress().getAddress2()));
        assertThat(customerDto.getAddress().getCity(), is(customer.getAddress().getCity()));
        assertThat(customerDto.getAddress().getZipCode(), is(customer.getAddress().getZipCode()));
        assertThat(customerDto.getAddress().getState(), is(customer.getAddress().getState()));
        assertThat(customerDto.getAddress().getCountry(), is(customer.getAddress().getCountry()));
    }

    @Test
    void testGetCustomerByCustomerId_WhenUserIdDoesNotExist() {
        String userId = "jdoe-servicetest-doesnotexist";
        CustomerDto customerDto = customerService.getCustomerByCustomerId(userId);
        assertThat(customerDto, nullValue());
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
