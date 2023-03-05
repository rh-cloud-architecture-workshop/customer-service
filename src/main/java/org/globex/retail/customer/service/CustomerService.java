package org.globex.retail.customer.service;

import io.smallrye.common.annotation.Blocking;
import org.globex.retail.customer.model.dto.CustomerDto;
import org.globex.retail.customer.model.dto.CustomerMapper;
import org.globex.retail.customer.repository.CustomerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    CustomerMapper mapper;

    @Transactional
    @Blocking
    public CustomerDto getCustomerByCustomerId(String userId) {
        return mapper.toDto(customerRepository.findByUserId(userId));
    }

}
