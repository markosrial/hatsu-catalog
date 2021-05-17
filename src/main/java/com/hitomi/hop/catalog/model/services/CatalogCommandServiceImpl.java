package com.hitomi.hop.catalog.model.services;

import com.hitomi.hop.catalog.i18n.I18N;
import com.hitomi.hop.catalog.model.domain.Genre;
import com.hitomi.hop.catalog.model.domain.Manga;
import com.hitomi.hop.catalog.model.domain.Serie;
import com.hitomi.hop.catalog.model.persistence.jpa.GenreDao;
import com.hitomi.hop.catalog.model.persistence.jpa.MangaDao;
import com.hitomi.hop.catalog.model.persistence.jpa.SerieDao;
import com.hitomi.hop.catalog.model.services.common.Block;
import com.hitomi.hop.catalog.model.services.exceptions.DuplicatedInstanceException;
import com.hitomi.hop.catalog.model.services.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatalogCommandServiceImpl implements CatalogCommandService {

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private SerieDao serieDao;

    @Autowired
    private MangaDao mangaDao;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAllGenres() {
        List<Genre> genreList = new ArrayList<>();
        genreDao.findAll(Sort.by(Sort.Direction.ASC, "name")).forEach(genreList::add);
        return genreList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Genre addGenre(String name) throws DuplicatedInstanceException {

        if (genreDao.findByNameIgnoreCase(name).isPresent()) {
            throw new DuplicatedInstanceException(I18N.ENTITY_GENRE, I18N.PROPERTY_NAME, name);
        }

        Genre newGenre = new Genre();
        newGenre.setName(StringUtils.capitalize(name));

        return genreDao.save(newGenre);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Genre updateGenre(Short id, String name) throws InstanceNotFoundException, DuplicatedInstanceException {

        if (genreDao.findByNameIgnoreCaseAndIdIsNot(name, id).isPresent()) {
            throw new DuplicatedInstanceException(I18N.ENTITY_GENRE, I18N.PROPERTY_NAME, name);
        }

        Genre genre = findGenreById(id);
        genre.setName(StringUtils.capitalize(name));

        return genreDao.save(genre);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteGenre(Integer id) throws InstanceNotFoundException {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Block<Serie> findSeries(String name, int page, int size) {
        Slice<Serie> series =
                serieDao.find(name, PageRequest.of(page, size, Sort.by("name").ascending()));
        return new Block<>(series.getContent(), series.hasNext());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Serie findSerieById(Integer id) throws InstanceNotFoundException {
        return serieDao.findById(id)
                .orElseThrow(() -> new InstanceNotFoundException(I18N.ENTITY_SERIE, I18N.PROPERTY_ID, id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Serie addSerie(String name, String description) throws DuplicatedInstanceException {

        if (serieDao.findByNameIgnoreCase(name).isPresent()) {
            throw new DuplicatedInstanceException(I18N.ENTITY_SERIE, I18N.PROPERTY_NAME, name);
        }

        Serie newSerie = new Serie();
        newSerie.setName(name);
        newSerie.setDescription(description);

        return serieDao.save(newSerie);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Serie updateSerie(Integer id, String name, String description) throws DuplicatedInstanceException, InstanceNotFoundException {

        Serie serie = findSerieById(id);
        if (name != null) {
            if (serieDao.findByNameIgnoreCaseAndIdNot(name, id).isPresent()) {
                throw new DuplicatedInstanceException(I18N.ENTITY_SERIE, I18N.PROPERTY_NAME, name);
            }
            serie.setName(name);
        }
        if (description != null) {
            serie.setDescription(description);
        }

        return serieDao.save(serie);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSerie(Integer id) throws InstanceNotFoundException {

    }

    @Override
    public Manga findMangaById(String sku) throws InstanceNotFoundException {
        return mangaDao.findById(sku)
                .orElseThrow(() -> new InstanceNotFoundException(I18N.ENTITY_MANGA, I18N.PROPERTY_SKU, sku));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Manga addManga(Manga manga) throws DuplicatedInstanceException {

        if (mangaDao.existsById(manga.getSku())) {
            throw new DuplicatedInstanceException(I18N.ENTITY_MANGA, I18N.PROPERTY_SKU, manga.getSku());
        }

        manga.setCoverImage(null);
        manga.setBackCoverImage(null);
        manga.setRemoved(false);

        return mangaDao.save(manga);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Manga updateManga(Manga updatedManga) throws InstanceNotFoundException, DuplicatedInstanceException {

        Manga manga = findMangaById(updatedManga.getSku());

        if (updatedManga.getTitle() != null) {
            if (mangaDao.findByTitleIgnoreCaseAndSkuNot(updatedManga.getTitle(), manga.getSku()).isPresent()) {
                throw new DuplicatedInstanceException(I18N.ENTITY_MANGA, I18N.PROPERTY_TITLE, manga.getTitle());
            }
            manga.setTitle(updatedManga.getTitle());
        }

        if (updatedManga.getBlurb() != null) {
            manga.setTitle(updatedManga.getBlurb());
        }

        if (updatedManga.getPages() != null) {
            manga.setPages(updatedManga.getPages());
        }

        if (updatedManga.getPublished() != null) {
            manga.setPublished(updatedManga.getPublished());
        }

        if (updatedManga.getSerieId() != null) {
            manga.setSerieId(updatedManga.getSerieId());
        }

        if (updatedManga.getEditorialId() != null) {
            manga.setSerieId(updatedManga.getEditorialId());
        }

        if (updatedManga.getGenres() != null) {
            manga.setGenres(updatedManga.getGenres());
        }

        if (updatedManga.getArtists() != null) {
            manga.setArtists(updatedManga.getArtists());
        }

        if (updatedManga.getWriters() != null) {
            manga.setWriters(updatedManga.getWriters());
        }

        if (updatedManga.getCoverImage() != null) {
            manga.setCoverImage(updatedManga.getCoverImage());
        }

        if (updatedManga.getBackCoverImage() != null) {
            manga.setBackCoverImage(updatedManga.getBackCoverImage());
        }

        return mangaDao.save(manga);

    }

    @Override
    public void removeManga(String sku) throws InstanceNotFoundException {

        Manga manga = findMangaById(sku);
        manga.setRemoved(true);
        mangaDao.save(manga);

    }

    @Override
    public void deleteManga(String sku) throws InstanceNotFoundException {
        findMangaById(sku);
        mangaDao.deleteById(sku);
    }

    /**
     * Service private utils
     */

    private Genre findGenreById(Short id) throws InstanceNotFoundException {
        return genreDao.findById(id).orElseThrow(
                () -> new InstanceNotFoundException(I18N.ENTITY_GENRE, I18N.PROPERTY_ID, id));
    }

}
