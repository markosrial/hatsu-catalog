package com.hitomi.hop.catalog.model.services;

import com.hitomi.hop.catalog.model.domain.Editorial;
import com.hitomi.hop.catalog.model.domain.Mangaka;
import com.hitomi.hop.catalog.model.services.common.Block;
import com.hitomi.hop.catalog.model.services.exceptions.DuplicatedInstanceException;
import com.hitomi.hop.catalog.model.services.exceptions.InstanceNotFoundException;

import java.time.LocalDate;

public interface StudioService {

    /**
     * Retrieves a subset of {@link Mangaka}
     * @param name partial name to match with the mangaka name
     * @param isoCountry the iso country of the mangaka
     * @param page the page number
     * @param size the size
     * @return the subset of mangakas
     */
    Block<Mangaka> findMangakas(String name, String isoCountry, int page, int size);

    /**
     * Retrieves a {@link Mangaka} info
     * @param id the mangaka identfier
     * @return the mangaka entity
     * @throws {@link InstanceNotFoundException}
     */
    Mangaka findMangakaById(Integer id) throws InstanceNotFoundException;

    /**
     * Add a new {@link Mangaka}
     * @param name the name
     * @param birthDate the birth date
     * @param isoCountry the iso code from native country
     * @return the inserted mangaka
     * @throws {@link DuplicatedInstanceException}
     */
    Mangaka addMangaka(String name, LocalDate birthDate, String isoCountry) throws DuplicatedInstanceException;

    /**
     * Update a existing {@link Mangaka}
     *
     * @param id             the mangaka identifier
     * @param name           the name
     * @param birthDate      the birth date
     * @param isoCountryCode the iso code from native country
     * @return the updated mangaka
     * @throws {@link InstanceNotFoundException}
     */
    Mangaka updateMangaka(Integer id, String name, LocalDate birthDate, String isoCountryCode)
            throws InstanceNotFoundException;


    /**
     * Deletes a {@link Mangaka}
     *
     * @param id the mangaka id
     * @throws {@link InstanceNotFoundException}
     */
    void deleteMangaka(Integer id) throws InstanceNotFoundException;

    /**
     * Retrieves a subset of {@link Editorial}
     *
     * @param name       partial name to match with the editorial name
     * @param isoCountry the iso country of the editorial
     * @param page       the page number
     * @param size       the size
     * @return the subset of editorial
     */
    Block<Editorial> findEditorial(String name, String isoCountry, int page, int size);

    /**
     * Retrieves a {@link Editorial} info
     * @param id the editorial identifier
     * @return the editorial entity
     * @throws {@link InstanceNotFoundException}
     */
    Editorial findEditorialById(Integer id) throws InstanceNotFoundException;

    /**
     * Add a new {@link Editorial}
     * @param name the editorial name
     * @param isoCountry the editorial iso country code
     * @param website the editorial web site
     * @return the inserted editorial
     * @throws {@link DuplicatedInstanceException}
     */
    Editorial addEditorial(String name, String isoCountry, String website) throws DuplicatedInstanceException;

    /**
     * Update a existing {@link Editorial}
     * @param id the editorial identifier
     * @param name the editorial name
     * @param isoCountry the editorial iso country code
     * @param website the editorial website
     * @return the updated editorial
     * @throws {@link DuplicatedInstanceException}, {@link InstanceNotFoundException}
     */
    Editorial updateEditorial(Integer id, String name, String isoCountry, String website)
            throws DuplicatedInstanceException, InstanceNotFoundException;


    /**
     * Deletes a {@link Editorial}
     *
     * @param id the editorial id
     * @throws {@link InstanceNotFoundException}
     */
    void deleteEditorial(Integer id) throws InstanceNotFoundException;

}
