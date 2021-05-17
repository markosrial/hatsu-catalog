package com.hitomi.hop.catalog.model.persistence.jpa;

import com.hitomi.hop.catalog.model.domain.Mangaka;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MangakaDao extends PagingAndSortingRepository<Mangaka, Integer>, CustomMangakaDao {

    Optional<Mangaka> findByNameIgnoreCase(String name);

}
