package com.hitomi.hop.catalog.model.persistence.jpa;

import com.hitomi.hop.catalog.model.domain.Genre;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface GenreDao extends PagingAndSortingRepository<Genre, Short> {

    Optional<Genre> findByNameIgnoreCase(String name);

    Optional<Genre> findByNameIgnoreCaseAndIdIsNot(String name, Short id);

}
