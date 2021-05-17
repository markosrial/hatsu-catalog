package com.hitomi.hop.catalog.model.services;

import com.hitomi.hop.catalog.model.domain.Genre;
import com.hitomi.hop.catalog.model.domain.Manga;
import com.hitomi.hop.catalog.model.domain.Serie;
import com.hitomi.hop.catalog.model.services.common.Block;
import com.hitomi.hop.catalog.model.services.exceptions.DuplicatedInstanceException;
import com.hitomi.hop.catalog.model.services.exceptions.InstanceNotFoundException;

import java.util.List;

public interface CatalogCommandService {

    /**
     * Retrieves all {@link Genre}
     *
     * @return the genre list
     */
    List<Genre> findAllGenres();

    /**
     * Add a new {@link Genre}
     *
     * @param name the genre name
     * @return the new genre
     * @throws {@link DuplicatedInstanceException}
     */
    Genre addGenre(String name) throws DuplicatedInstanceException;

    /**
     * Updates the {@link Genre}
     *
     * @param id   the genre identifier
     * @param name the new name
     * @return the updated genre
     * @throws {@link InstanceNotFoundException}, {@link DuplicatedInstanceException}
     */
    Genre updateGenre(Short id, String name) throws InstanceNotFoundException, DuplicatedInstanceException;


    /**
     * Deletes a {@link Genre} from catalog
     *
     * @param id the genre id
     * @throws {@link InstanceNotFoundException}
     */
    void deleteGenre(Integer id) throws InstanceNotFoundException;

    /**
     * Retrieves a subset of {@link Serie}
     *
     * @param name partial name to match with the serie
     * @param page the page number
     * @param size the page size
     * @return the subset of series
     */
    Block<Serie> findSeries(String name, int page, int size);

    /**
     * Retrieves a {@link Serie} info
     *
     * @param id the serie identifier
     * @return the serie entity
     * @throws {@link InstanceNotFoundException}
     */
    Serie findSerieById(Integer id) throws InstanceNotFoundException;

    /**
     * Add a new {@link Serie}
     *
     * @param name the serie name
     * @param description the serie description
     * @return the new serie
     * @throws {@link DuplicatedInstanceException}
     */
    Serie addSerie(String name, String description) throws DuplicatedInstanceException;

    /**
     * Updates a {@link Serie}
     *
     * @param id          the serie identifier
     * @param name        the new serie name
     * @param description the new serie description
     * @return the updated serie
     * @throws {@link DuplicatedInstanceException}, {@link InstanceNotFoundException}
     */
    Serie updateSerie(Integer id, String name, String description) throws DuplicatedInstanceException, InstanceNotFoundException;

    /**
     * Deletes a {@link Serie} from catalog
     *
     * @param id the serie id
     * @throws {@link InstanceNotFoundException}
     */
    void deleteSerie(Integer id) throws InstanceNotFoundException;

    /**
     * Retrieves a {@link Manga} by the sku
     *
     * @param sku the managa identifier
     * @return the manga entity
     * @throws {@link InstanceNotFoundException}
     */
    Manga findMangaById(String sku) throws InstanceNotFoundException;

    /**
     * Add a new {@link Manga}
     *
     * @param manga the manga with initial data
     * @return the inserted manga
     * @throws {@link DuplicatedInstanceException}
     */
    Manga addManga(Manga manga) throws DuplicatedInstanceException;

    /**
     * Updates the {@link Manga}
     *
     * @param updatedManga the manga with properties to update
     * @return the updated manga
     * @throws {@link InstanceNotFoundException}, {@link DuplicatedInstanceException}
     */
    Manga updateManga(Manga updatedManga) throws InstanceNotFoundException, DuplicatedInstanceException;

    /**
     * Removes a {@link Manga} from catalog
     * The manga data is kept unless a delete is performed
     *
     * @param sku the manga indentifier
     * @throws {@link InstanceNotFoundException}
     */
    void removeManga(String sku) throws InstanceNotFoundException;

    /**
     * Deletes permanently a {@link Manga}
     *
     * @param sku the manga identifier
     * @throws {@link InstanceNotFoundException}, {@link DuplicatedInstanceException}
     */
    void deleteManga(String sku) throws InstanceNotFoundException;

}
