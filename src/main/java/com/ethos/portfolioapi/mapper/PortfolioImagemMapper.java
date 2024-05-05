package com.ethos.portfolioapi.mapper;

import com.ethos.portfolioapi.controller.response.PortfolioImagemResponse;
import com.ethos.portfolioapi.model.Portfolio;
import org.mapstruct.Mapper;

@Mapper
public interface PortfolioImagemMapper {

Portfolio from (PortfolioImagemResponse portfolioImagemResponse);
}
