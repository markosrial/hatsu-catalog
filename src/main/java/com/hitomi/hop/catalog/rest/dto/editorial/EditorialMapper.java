package com.hitomi.hop.catalog.rest.dto.editorial;

import com.hitomi.hop.catalog.model.domain.Editorial;
import com.hitomi.hop.catalog.model.services.common.Block;
import com.hitomi.hop.catalog.rest.dto.common.BlockDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EditorialMapper {

    EditorialMapper INSTANCE = Mappers.getMapper(EditorialMapper.class);

    @Mapping(source = "created", target = "inserted")
    @Mapping(source = "updated", target = "updated")
    EditorialDto toEditorialDto(Editorial editorial);

    BlockDto<EditorialDto> toEditorialDtos(Block<Editorial> editorial);

}
