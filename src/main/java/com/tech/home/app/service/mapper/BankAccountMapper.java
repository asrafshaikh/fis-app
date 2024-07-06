package com.tech.home.app.service.mapper;

import com.tech.home.app.domain.BankAccount;
import com.tech.home.app.domain.Customer;
import com.tech.home.app.service.dto.BankAccountDTO;
import com.tech.home.app.service.dto.CustomerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BankAccount} and its DTO {@link BankAccountDTO}.
 */
@Mapper(componentModel = "spring")
public interface BankAccountMapper extends EntityMapper<BankAccountDTO, BankAccount> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerId")
    BankAccountDTO toDto(BankAccount s);

    @Named("customerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomerDTO toDtoCustomerId(Customer customer);
}
