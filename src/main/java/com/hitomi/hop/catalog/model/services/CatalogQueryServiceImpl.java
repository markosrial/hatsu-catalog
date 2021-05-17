package com.hitomi.hop.catalog.model.services;

import com.hitomi.hop.catalog.i18n.I18N;
import com.hitomi.hop.catalog.model.domain.MangaDoc;
import com.hitomi.hop.catalog.model.persistence.search.MangaQueryDao;
import com.hitomi.hop.catalog.model.services.common.PageBlock;
import com.hitomi.hop.catalog.model.services.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CatalogQueryServiceImpl implements CatalogQueryService {

    @Autowired
    private MangaQueryDao mangaQueryDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public PageBlock<MangaDoc> findSerieRelatedMangas(Integer serieId, int page, int size) {

        Page<MangaDoc> mangas = mangaQueryDao.findAllBySerieId(
                serieId, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "published")));

        return new PageBlock<>(mangas.getContent(), mangas.getTotalPages());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageBlock<MangaDoc> findMangakaRelatedWork(Integer mangakaId, int page, int size) {

        Page<MangaDoc> mangas = mangaQueryDao.findAllByArtistsIsOrWritersIs(
                mangakaId, mangakaId, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "published")));

        return new PageBlock<>(mangas.getContent(), mangas.getTotalPages());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageBlock<MangaDoc> findMangas(int page, int size) {
        Page<MangaDoc> mangas = mangaQueryDao.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "published")));

        return new PageBlock<>(mangas.getContent(), mangas.getTotalPages());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MangaDoc findManga(String sku) throws InstanceNotFoundException {
        return mangaQueryDao.findById(sku)
                .orElseThrow(() -> new InstanceNotFoundException(I18N.ENTITY_MANGA, I18N.PROPERTY_SKU, sku));
    }

}
