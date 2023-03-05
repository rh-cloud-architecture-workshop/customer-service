package org.globex.retail.customer.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.globex.retail.customer.model.entity.Customer;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    public Customer findByUserId(String userId) {
        return find("userId", userId).firstResult();
    }

    public Customer findByEmail(String email) {
        return find("email", email).firstResult();
    }
}
