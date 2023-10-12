package com.ethos.portfolioapi.mapper;

import com.ethos.portfolioapi.model.Portfolio;
import com.ethos.portfolioapi.repository.entity.PortfolioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PortfolioEntityMapper {
    PortfolioEntity from (Portfolio portfolio);

}
