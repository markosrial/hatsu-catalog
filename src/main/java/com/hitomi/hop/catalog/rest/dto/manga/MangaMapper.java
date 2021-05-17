package com.hitomi.hop.catalog.rest.dto.manga;

import com.hitomi.hop.catalog.model.domain.Manga;
import com.hitomi.hop.catalog.model.domain.MangaDoc;
import com.hitomi.hop.catalog.model.services.common.PageBlock;
import com.hitomi.hop.catalog.rest.dto.common.PageBlockDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MangaMapper {

    MangaMapper INSTANCE = Mappers.getMapper(MangaMapper.class);

    @Mapping(source = "created", target = "inserted")
    @Mapping(source = "updated", target = "updated")
    MangaDto toMangaDto(Manga manga);

    @Mapping(source = "created", target = "inserted")
    @Mapping(source = "updated", target = "updated")
    MangaDto toMangaDto(MangaDoc manga);

    PageBlockDto<MangaDto> toMangaDtos(PageBlock<MangaDoc> mangas);

}
