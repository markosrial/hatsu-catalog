package com.hitomi.hop.catalog.rest.controller;

import com.hitomi.hop.catalog.model.services.StudioService;
import com.hitomi.hop.catalog.model.services.exceptions.DuplicatedInstanceException;
import com.hitomi.hop.catalog.model.services.exceptions.InstanceNotFoundException;
import com.hitomi.hop.catalog.rest.dto.common.BlockDto;
import com.hitomi.hop.catalog.rest.dto.editorial.EditorialDto;
import com.hitomi.hop.catalog.rest.dto.editorial.EditorialMapper;
import com.hitomi.hop.catalog.rest.dto.editorial.EditorialSearchParamsDto;
import com.hitomi.hop.catalog.rest.dto.editorial.InsertEditorialParamsDto;
import com.hitomi.hop.catalog.rest.dto.mangaka.InsertMangakaParamsDto;
import com.hitomi.hop.catalog.rest.dto.mangaka.MangakaDto;
import com.hitomi.hop.catalog.rest.dto.mangaka.MangakaMapper;
import com.hitomi.hop.catalog.rest.dto.mangaka.MangakaSearchParamsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studio")
public class StudioController {

    @Autowired
    private StudioService studioService;

    @GetMapping("/mangakas")
    @PreAuthorize("permitAll()")
    public BlockDto<MangakaDto> getMangakasSummary(@Validated @RequestBody MangakaSearchParamsDto searchParams) {

        return MangakaMapper.INSTANCE.toMangakaDtos(
                studioService.findMangakas(
                        searchParams.getName(),
                        searchParams.getIsoCountry(),
                        searchParams.getPage(),
                        searchParams.getSize()));

    }

    @GetMapping("/mangakas/{id}")
    @PreAuthorize("permitAll()")
    public MangakaDto getMangaka(@PathVariable Integer id) throws InstanceNotFoundException {
        return MangakaMapper.INSTANCE.toMangakaDto(studioService.findMangakaById(id));
    }

    @PostMapping("/mangakas")
    @PreAuthorize("permitAll()")
    public MangakaDto addMangaka(
            @Validated({InsertMangakaParamsDto.AddValidations.class}) @RequestBody InsertMangakaParamsDto params)
            throws DuplicatedInstanceException {

        return MangakaMapper.INSTANCE.toMangakaDto(
            studioService.addMangaka(
                params.getName(),
                params.getBirthDate(),
                params.getIsoCountry()));

    }

    @PutMapping("/mangakas/{id}")
    @PreAuthorize("permitAll()")
    public MangakaDto updateMangaka(
            @PathVariable Integer id,
            @Validated({InsertMangakaParamsDto.UpdateValidations.class}) @RequestBody InsertMangakaParamsDto params)
            throws DuplicatedInstanceException, InstanceNotFoundException {

        return MangakaMapper.INSTANCE.toMangakaDto(
                studioService.updateMangaka(
                        id,
                        params.getName(),
                        params.getBirthDate(),
                        params.getIsoCountry()));

    }

    @GetMapping("/editorials")
    @PreAuthorize("permitAll()")
    public BlockDto<EditorialDto> getEditorialsSummary(
            @Validated @RequestBody EditorialSearchParamsDto searchParams) {

        return EditorialMapper.INSTANCE.toEditorialDtos(
                studioService.findEditorial(
                        searchParams.getName(),
                        searchParams.getIsoCountry(),
                        searchParams.getPage(),
                        searchParams.getSize()));

    }

    @GetMapping("/editorials/{id}")
    @PreAuthorize("permitAll()")
    public EditorialDto getEditorial(@PathVariable Integer id) throws InstanceNotFoundException {
        return EditorialMapper.INSTANCE.toEditorialDto(studioService.findEditorialById(id));
    }

    @PostMapping("/editorials")
    @PreAuthorize("permitAll()")
    public EditorialDto addEditorial(
            @Validated({InsertEditorialParamsDto.AddValidations.class}) @RequestBody InsertEditorialParamsDto params)
            throws DuplicatedInstanceException {

        return EditorialMapper.INSTANCE.toEditorialDto(
                studioService.addEditorial(
                        params.getName(),
                        params.getIsoCountry(),
                        params.getWebsite()));

    }

    @PutMapping("/editorials/{id}")
    @PreAuthorize("permitAll()")
    public EditorialDto updateEditorial(
            @PathVariable Integer id,
            @Validated({InsertEditorialParamsDto.UpdateValidations.class}) @RequestBody InsertEditorialParamsDto params)
            throws DuplicatedInstanceException, InstanceNotFoundException {

        return EditorialMapper.INSTANCE.toEditorialDto(
                studioService.updateEditorial(
                        id,
                        params.getName(),
                        params.getIsoCountry(),
                        params.getWebsite()));

    }

}
