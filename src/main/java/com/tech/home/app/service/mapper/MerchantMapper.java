package com.tech.home.app.service.mapper;

import com.tech.home.app.domain.Merchant;
import com.tech.home.app.service.dto.MerchantDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Merchant} and its DTO {@link MerchantDTO}.
 */
@Mapper(componentModel = "spring")
public interface MerchantMapper extends EntityMapper<MerchantDTO, Merchant> {}
