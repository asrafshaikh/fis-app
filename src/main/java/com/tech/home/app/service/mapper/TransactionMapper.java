package com.tech.home.app.service.mapper;

import com.tech.home.app.domain.BankAccount;
import com.tech.home.app.domain.Transaction;
import com.tech.home.app.service.dto.BankAccountDTO;
import com.tech.home.app.service.dto.TransactionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Transaction} and its DTO {@link TransactionDTO}.
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction> {
    @Mapping(target = "bankAccount", source = "bankAccount", qualifiedByName = "bankAccountId")
    TransactionDTO toDto(Transaction s);

    @Named("bankAccountId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BankAccountDTO toDtoBankAccountId(BankAccount bankAccount);
}
