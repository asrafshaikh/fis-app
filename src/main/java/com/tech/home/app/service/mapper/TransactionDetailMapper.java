package com.tech.home.app.service.mapper;

import com.tech.home.app.domain.Merchant;
import com.tech.home.app.domain.Transaction;
import com.tech.home.app.domain.TransactionDetail;
import com.tech.home.app.service.dto.MerchantDTO;
import com.tech.home.app.service.dto.TransactionDTO;
import com.tech.home.app.service.dto.TransactionDetailDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TransactionDetail} and its DTO {@link TransactionDetailDTO}.
 */
@Mapper(componentModel = "spring")
public interface TransactionDetailMapper extends EntityMapper<TransactionDetailDTO, TransactionDetail> {
    @Mapping(target = "merchant", source = "merchant", qualifiedByName = "merchantId")
    @Mapping(target = "transaction", source = "transaction", qualifiedByName = "transactionId")
    TransactionDetailDTO toDto(TransactionDetail s);

    @Named("merchantId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    MerchantDTO toDtoMerchantId(Merchant merchant);

    @Named("transactionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TransactionDTO toDtoTransactionId(Transaction transaction);
}
