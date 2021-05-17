package com.hitomi.hop.catalog.rest.dto.serie;

import com.hitomi.hop.catalog.model.domain.Serie;
import com.hitomi.hop.catalog.model.services.common.Block;
import com.hitomi.hop.catalog.rest.dto.common.BlockDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SerieMapper {

    SerieMapper INSTANCE = Mappers.getMapper(SerieMapper.class);

    @Mapping(source = "created", target = "inserted")
    @Mapping(source = "updated", target = "updated")
    SerieDto toSerieDto(Serie serie);

    BlockDto<SerieDto> toSerieDtos(Block<Serie> series);

}
