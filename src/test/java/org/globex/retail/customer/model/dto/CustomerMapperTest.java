package org.globex.retail.customer.model.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.globex.retail.customer.model.entity.Address;
import org.globex.retail.customer.model.entity.Customer;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class CustomerMapperTest {

    @Inject
    CustomerMapper mapper;

    @Test
    void testMapToDto() {
        Customer customer = buildCustomer();
        CustomerDto customerDto = mapper.toDto(customer);
        assertThat(customerDto, notNullValue());
        assertThat(customerDto.getUserId(), is(customer.getUserId()));
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

    private Customer buildCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setUserId("jdoe");
        customer.setEmail("jdoe@email.com");
        customer.setPhone("111 222 332 444");
        Address address = new Address();
        address.setId(1);
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
