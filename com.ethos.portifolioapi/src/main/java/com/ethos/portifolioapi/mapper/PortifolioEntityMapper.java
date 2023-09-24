package com.ethos.portifolioapi.mapper;

import com.ethos.portifolioapi.model.Portifolio;
import com.ethos.portifolioapi.repository.entity.PortifolioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PortifolioEntityMapper {
    PortifolioEntity from (Portifolio portifolio);

}
