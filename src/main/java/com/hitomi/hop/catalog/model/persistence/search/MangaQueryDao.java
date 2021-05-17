package com.hitomi.hop.catalog.model.persistence.search;

import com.hitomi.hop.catalog.model.domain.MangaDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MangaQueryDao extends ElasticsearchRepository<MangaDoc, String> {

    Page<MangaDoc> findAllBySerieId(Integer serieId, Pageable page);

    Page<MangaDoc> findAllByArtistsIsOrWritersIs(Integer mangakaId1, Integer mangakaId2, Pageable page);

}
