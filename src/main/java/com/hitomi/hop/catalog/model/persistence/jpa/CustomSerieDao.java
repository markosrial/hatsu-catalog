package com.hitomi.hop.catalog.model.persistence.jpa;

import com.hitomi.hop.catalog.model.domain.Serie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CustomSerieDao {

    Slice<Serie> find(String keyword, Pageable page);

}
