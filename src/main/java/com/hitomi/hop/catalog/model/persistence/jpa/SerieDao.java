package com.hitomi.hop.catalog.model.persistence.jpa;

import com.hitomi.hop.catalog.model.domain.Serie;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SerieDao extends PagingAndSortingRepository<Serie, Integer>, CustomSerieDao {

    Optional<Serie> findByNameIgnoreCase(String name);

    Optional<Serie> findByNameIgnoreCaseAndIdNot(String name, Integer id);

}
