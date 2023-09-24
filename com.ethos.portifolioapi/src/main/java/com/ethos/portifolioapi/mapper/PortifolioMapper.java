package com.ethos.portifolioapi.mapper;

import com.ethos.portifolioapi.controller.request.PortifolioRequest;
import com.ethos.portifolioapi.model.Portifolio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", nullValueMappingStrategy = org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT)
public interface PortifolioMapper {
    Portifolio from(PortifolioRequest portifolioRequest);
}
