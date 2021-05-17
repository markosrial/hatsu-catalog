package com.hitomi.hop.catalog.model.persistence.jpa;

import com.hitomi.hop.catalog.model.domain.Editorial;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EditorialDao extends PagingAndSortingRepository<Editorial, Integer>, CustomEditorialDao {

    Optional<Editorial> findByNameIgnoreCase(String name);

    Optional<Editorial> findByNameIgnoreCaseAndIdNot(String name, Integer id);

}
