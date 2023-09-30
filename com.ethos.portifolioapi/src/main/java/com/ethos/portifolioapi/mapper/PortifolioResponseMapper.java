package com.ethos.portifolioapi.mapper;

import com.ethos.portifolioapi.controller.response.PortifolioResponse;
import com.ethos.portifolioapi.repository.entity.PortifolioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", nullValueMappingStrategy = org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT) public interface PortifolioResponseMapper {
    PortifolioResponse from(PortifolioEntity portifolioEntity);
}
