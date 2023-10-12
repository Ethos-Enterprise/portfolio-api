package com.ethos.portfolioapi.mapper;

import com.ethos.portfolioapi.controller.request.PortfolioRequest;
import com.ethos.portfolioapi.model.Portfolio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", nullValueMappingStrategy = org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT)
public interface PortfolioMapper {
    Portfolio from(PortfolioRequest portfolioRequest);
}
