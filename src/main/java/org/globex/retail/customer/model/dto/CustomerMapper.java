package org.globex.retail.customer.model.dto;

import org.globex.retail.customer.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi", uses = { AddressMapper.class })
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

}
