package com.ethos.portfolioapi.mapper;

import com.ethos.portfolioapi.controller.response.PortfolioResponse;
import com.ethos.portfolioapi.repository.entity.PortfolioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", nullValueMappingStrategy = org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT) public interface PortfolioResponseMapper {
    PortfolioResponse from(PortfolioEntity portfolioEntity);
}
