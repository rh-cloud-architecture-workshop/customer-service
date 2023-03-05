package org.globex.retail.customer.model.dto;

import org.globex.retail.customer.model.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface AddressMapper {

    AddressDto toDto(Address address);

}
