package com.hitomi.hop.catalog.model.persistence.jpa;

import com.hitomi.hop.catalog.model.domain.Editorial;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CustomEditorialDao {

    Slice<Editorial> find(String keyword, String isoCountry, Pageable page);

}
