package com.hitomi.hop.catalog.rest.dto.mangaka;

import com.hitomi.hop.catalog.model.domain.Mangaka;
import com.hitomi.hop.catalog.model.services.common.Block;
import com.hitomi.hop.catalog.rest.dto.common.BlockDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MangakaMapper {

    MangakaMapper INSTANCE = Mappers.getMapper(MangakaMapper.class);

    @Mapping(source = "created", target = "inserted")
    @Mapping(source = "updated", target = "updated")
    MangakaDto toMangakaDto(Mangaka mangaka);

    BlockDto<MangakaDto> toMangakaDtos(Block<Mangaka> mangaka);

}
