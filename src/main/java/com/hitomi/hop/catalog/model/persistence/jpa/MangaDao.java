package com.hitomi.hop.catalog.model.persistence.jpa;

import com.hitomi.hop.catalog.model.domain.Manga;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MangaDao extends PagingAndSortingRepository<Manga, String> {

    Optional<Manga> findByTitleIgnoreCaseAndSkuNot(String title, String sku);

}

