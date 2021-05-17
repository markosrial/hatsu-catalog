package com.hitomi.hop.catalog.rest.dto.genre;

import com.hitomi.hop.catalog.model.domain.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    /**
     * Maps a genre to a genre dto
     * @param genre the genre entity
     * @return the genre dto
     */
    GenreDto toGenreDto(Genre genre);

    /**
     * Converts a list of genres to a list of genre dtos
     * @param genres the genres list
     * @return the genre dtos list
     */
    List<GenreDto> toGenreDtos(List<Genre> genres);

}
