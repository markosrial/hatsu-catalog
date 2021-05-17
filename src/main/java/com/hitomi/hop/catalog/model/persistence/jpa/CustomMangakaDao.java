package com.hitomi.hop.catalog.model.persistence.jpa;

import com.hitomi.hop.catalog.model.domain.Mangaka;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CustomMangakaDao {

    Slice<Mangaka> find(String keyword, String isoCountry, Pageable page);

}
