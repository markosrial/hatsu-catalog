package com.hitomi.hop.catalog.rest.controller;

import com.hitomi.hop.catalog.model.domain.Manga;
import com.hitomi.hop.catalog.model.domain.MangaDoc;
import com.hitomi.hop.catalog.model.persistence.search.MangaQueryDao;
import com.hitomi.hop.catalog.model.services.CatalogCommandService;
import com.hitomi.hop.catalog.model.services.CatalogQueryService;
import com.hitomi.hop.catalog.model.services.exceptions.DuplicatedInstanceException;
import com.hitomi.hop.catalog.model.services.exceptions.InstanceNotFoundException;
import com.hitomi.hop.catalog.rest.dto.common.BlockDto;
import com.hitomi.hop.catalog.rest.dto.common.PageBlockDto;
import com.hitomi.hop.catalog.rest.dto.genre.GenreDto;
import com.hitomi.hop.catalog.rest.dto.genre.GenreMapper;
import com.hitomi.hop.catalog.rest.dto.genre.InsertGenreParamsDto;
import com.hitomi.hop.catalog.rest.dto.manga.InsertMangaParamsDto;
import com.hitomi.hop.catalog.rest.dto.manga.MangaDto;
import com.hitomi.hop.catalog.rest.dto.manga.MangaMapper;
import com.hitomi.hop.catalog.rest.dto.manga.MangaSearchParamsDto;
import com.hitomi.hop.catalog.rest.dto.serie.InsertSerieParamsDto;
import com.hitomi.hop.catalog.rest.dto.serie.SerieDto;
import com.hitomi.hop.catalog.rest.dto.serie.SerieMapper;
import com.hitomi.hop.catalog.rest.dto.serie.SerieSearchParamsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private CatalogCommandService catalogCommandService;

    @Autowired
    private CatalogQueryService catalogQueryService;

    @Autowired
    private MangaQueryDao mangaQueryDao;

    @GetMapping("/genres")
    @PreAuthorize("permitAll()")
    public List<GenreDto> getGenres() {
        return GenreMapper.INSTANCE.toGenreDtos(catalogCommandService.findAllGenres());
    }

    @PostMapping("/genres")
    @PreAuthorize("permitAll()")
    public GenreDto addGenre(@Validated @RequestBody InsertGenreParamsDto params)
            throws DuplicatedInstanceException {
        return GenreMapper.INSTANCE.toGenreDto(catalogCommandService.addGenre(params.getName()));
    }

    @PutMapping("/genres/{id}")
    @PreAuthorize("permitAll()")
    public GenreDto updateGenre(@PathVariable Short id, @Validated @RequestBody InsertGenreParamsDto params)
            throws InstanceNotFoundException, DuplicatedInstanceException {
        return GenreMapper.INSTANCE.toGenreDto(catalogCommandService.updateGenre(id, params.getName()));
    }

    @GetMapping("/series")
    @PreAuthorize("permitAll()")
    public BlockDto<SerieDto> getSeriesSummary(
            @Validated @RequestBody SerieSearchParamsDto searchParams) {

        return SerieMapper.INSTANCE.toSerieDtos(
                catalogCommandService.findSeries(
                        searchParams.getName(),
                        searchParams.getPage(),
                        searchParams.getSize()));

    }

    @GetMapping("/series/{id}")
    @PreAuthorize("permitAll()")
    public SerieDto getSerie(@PathVariable Integer id) throws InstanceNotFoundException {
        return SerieMapper.INSTANCE.toSerieDto(catalogCommandService.findSerieById(id));
    }

    @PostMapping("/series")
    @PreAuthorize("permitAll()")
    public SerieDto addSerie(
            @Validated({InsertSerieParamsDto.AddValidations.class}) @RequestBody InsertSerieParamsDto params)
            throws DuplicatedInstanceException {

        return SerieMapper.INSTANCE.toSerieDto(
                catalogCommandService.addSerie(
                        params.getName(),
                        params.getDescription()));

    }

    @PutMapping("/series/{id}")
    @PreAuthorize("permitAll()")
    public SerieDto updateSerie(
            @PathVariable Integer id,
            @Validated({InsertSerieParamsDto.UpdateValidations.class}) @RequestBody InsertSerieParamsDto params)
            throws DuplicatedInstanceException, InstanceNotFoundException {

        return SerieMapper.INSTANCE.toSerieDto(
                catalogCommandService.updateSerie(
                        id,
                        params.getName(),
                        params.getDescription()));

    }

    @GetMapping("/mangas/mangaka/{id}")
    @PreAuthorize("permitAll()")
    public PageBlockDto<MangaDto> getMangakaWork(@PathVariable Integer id, @Validated @RequestBody MangaSearchParamsDto params) {

        return MangaMapper.INSTANCE.toMangaDtos(
                catalogQueryService.findMangakaRelatedWork(
                        id,
                        params.getPage(),
                        params.getSize()));

    }

    @GetMapping("/mangas/serie/{id}")
    @PreAuthorize("permitAll()")
    public PageBlockDto<MangaDto> getSerieRelatedMangas(
            @PathVariable Integer id, @Validated @RequestBody MangaSearchParamsDto params) {

        return MangaMapper.INSTANCE.toMangaDtos(
                catalogQueryService.findSerieRelatedMangas(
                        id,
                        params.getPage(),
                        params.getSize()));

    }

    @GetMapping("/mangas/{sku}")
    @PreAuthorize("permitAll()")
    public MangaDto getMangaBySku(
            @PathVariable String sku) throws InstanceNotFoundException {

        return MangaMapper.INSTANCE.toMangaDto(catalogQueryService.findManga(sku));

    }

    @PostMapping("/mangas")
    @PreAuthorize("permitAll()")
    public MangaDto addManga(
            @Validated(InsertMangaParamsDto.AddValidations.class) @RequestBody InsertMangaParamsDto params)
            throws DuplicatedInstanceException {

        Manga manga = catalogCommandService.addManga(Manga.builder()
                .sku(params.getSku())
                .title(params.getTitle())
                .blurb(params.getBlurb())
                .pages(params.getPages())
                .published(params.getPublished())
                .serieId(params.getSerieId())
                .editorialId(params.getEditorialId())
                .genres(params.getGenres())
                .artists(params.getArtists())
                .writers(params.getWriters())
                .build());

        // TODO temporal - must remove this
        // -- START --
        MangaDoc doc = new MangaDoc();
        doc.setSku(params.getSku());
        doc.setSku(params.getSku());
        doc.setTitle(params.getTitle());
        doc.setBlurb(params.getBlurb());
        doc.setPages(params.getPages());
        doc.setPublished(params.getPublished());
        doc.setSerieId(params.getSerieId());
        doc.setEditorialId(params.getEditorialId());
        doc.setGenres(params.getGenres());
        doc.setArtists(params.getArtists());
        doc.setWriters(params.getWriters());
        doc.setCreated(manga.getCreated());
        doc.setUpdated(manga.getUpdated());
        mangaQueryDao.save(doc);
        // -- END --

        return MangaMapper.INSTANCE.toMangaDto(manga);

    }

    @PostMapping("/mangas/{sku}")
    @PreAuthorize("permitAll()")
    public MangaDto updateManga(
            @PathVariable String sku,
            @Validated(InsertMangaParamsDto.UpdateValidations.class) @RequestBody InsertMangaParamsDto params)
            throws InstanceNotFoundException, DuplicatedInstanceException {

        Manga manga = catalogCommandService.updateManga(Manga.builder()
                .sku(sku)
                .title(params.getTitle())
                .blurb(params.getBlurb())
                .pages(params.getPages())
                .published(params.getPublished())
                .serieId(params.getSerieId())
                .editorialId(params.getEditorialId())
                .genres(params.getGenres())
                .artists(params.getArtists())
                .writers(params.getWriters())
                .build());

        return MangaMapper.INSTANCE.toMangaDto(manga);

    }

}
