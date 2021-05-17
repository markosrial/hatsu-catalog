package com.hitomi.hop.catalog.model.services;

import com.hitomi.hop.catalog.model.domain.MangaDoc;
import com.hitomi.hop.catalog.model.services.common.PageBlock;
import com.hitomi.hop.catalog.model.services.exceptions.InstanceNotFoundException;

public interface CatalogQueryService {

    /**
     * Gets the {@link MangaDoc} related to a serie
     * @param serieId the serie identifier
     * @param page the page number
     * @param size the page size
     * @return the subset of mangas
     */
    PageBlock<MangaDoc> findSerieRelatedMangas(Integer serieId, int page, int size);

    /**
     * Gets the {@link MangaDoc} related to a mangaka
     * @param mangakaId the mangaka identifier
     * @param page the page number
     * @param size the page size
     * @return the subset of mangas
     */
    PageBlock<MangaDoc> findMangakaRelatedWork(Integer mangakaId, int page, int size);

    /**
     * Gets a {@link MangaDoc} page
     *
     * @param page the page number
     * @param size the page size
     * @return the subset of manga
     */
    PageBlock<MangaDoc> findMangas(/**MangaDocFilters filters, **/int page, int size);

    /**
     * Get a {@link MangaDoc} by the sku
     * @param sku the sku reference
     * @return the manga
     * @throws {@link InstanceNotFoundException}
     */
    MangaDoc findManga(String sku) throws InstanceNotFoundException;

}
