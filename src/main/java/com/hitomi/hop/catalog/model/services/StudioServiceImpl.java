package com.hitomi.hop.catalog.model.services;

import com.hitomi.hop.catalog.i18n.I18N;
import com.hitomi.hop.catalog.model.domain.Editorial;
import com.hitomi.hop.catalog.model.domain.Mangaka;
import com.hitomi.hop.catalog.model.persistence.jpa.EditorialDao;
import com.hitomi.hop.catalog.model.persistence.jpa.MangakaDao;
import com.hitomi.hop.catalog.model.services.common.Block;
import com.hitomi.hop.catalog.model.services.exceptions.DuplicatedInstanceException;
import com.hitomi.hop.catalog.model.services.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class StudioServiceImpl implements StudioService {

    @Autowired
    private MangakaDao mangakaDao;

    @Autowired
    private EditorialDao editorialDao;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Block<Mangaka> findMangakas(String name, String isoCountry, int page, int size) {

        Slice<Mangaka> mangakaList =
                mangakaDao.find(name, isoCountry, PageRequest.of(page, size, Sort.by("name").ascending()));

        return new Block<>(mangakaList.getContent(), mangakaList.hasNext());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Mangaka findMangakaById(Integer id) throws InstanceNotFoundException {
        return mangakaDao.findById(id)
                .orElseThrow(() -> new InstanceNotFoundException(I18N.ENTITY_MANGAKA, I18N.PROPERTY_ID, id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mangaka addMangaka(String name, LocalDate birthDate, String isoCountry) throws DuplicatedInstanceException {

        Optional<Mangaka> mangaka = mangakaDao.findByNameIgnoreCase(name);
        if (mangaka.isPresent()) {
            throw new DuplicatedInstanceException(I18N.ENTITY_MANGAKA,
                    I18N.PROPERTY_NAME,
                    mangaka.get().getName());
        }

        return mangakaDao.save(
                Mangaka.builder()
                        .name(name)
                        .birthdate(birthDate)
                        .isoCountry(isoCountry)
                        .build()
        );

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mangaka updateMangaka(Integer id, String name, LocalDate birthDate, String isoCountry)
            throws InstanceNotFoundException {

        Mangaka mangaka = findMangakaById(id);

        if (name != null) {
            mangaka.setName(name);
        }

        if (birthDate != null) {
            mangaka.setBirthdate(birthDate);
        }

        if (isoCountry != null) {
            mangaka.setIsoCountry(isoCountry);
        }

        return mangakaDao.save(mangaka);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMangaka(Integer id) throws InstanceNotFoundException {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Block<Editorial> findEditorial(String name, String isoCountry, int page, int size) {
        Slice<Editorial> editorialList =
                editorialDao.find(name, isoCountry, PageRequest.of(page, size, Sort.by("name").ascending()));

        return new Block<>(editorialList.getContent(), editorialList.hasNext());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Editorial findEditorialById(Integer id) throws InstanceNotFoundException {
        return editorialDao.findById(id)
                .orElseThrow(() -> new InstanceNotFoundException(I18N.ENTITY_EDITORIAL, I18N.PROPERTY_ID, id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Editorial addEditorial(String name, String isoCountry, String website) throws DuplicatedInstanceException {

        Optional<Editorial> editorial = editorialDao.findByNameIgnoreCase(name);
        if (editorial.isPresent()) {
            throw new DuplicatedInstanceException(I18N.ENTITY_EDITORIAL,
                    I18N.PROPERTY_NAME,
                    editorial.get().getName());
        }

        return editorialDao.save(
                Editorial.builder()
                        .name(name)
                        .isoCountry(isoCountry)
                        .website(website)
                        .build()
        );

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Editorial updateEditorial(Integer id, String name, String isoCountry, String website)
            throws DuplicatedInstanceException, InstanceNotFoundException {

        Editorial editorial = findEditorialById(id);

        if (name != null) {
            if (editorialDao.findByNameIgnoreCaseAndIdNot(name, id).isPresent()) {
                throw new DuplicatedInstanceException(I18N.ENTITY_EDITORIAL, I18N.PROPERTY_NAME, name);
            }
            editorial.setName(name);
        }

        if (isoCountry != null) {
            editorial.setIsoCountry(isoCountry);
        }

        if (website != null) {
            editorial.setWebsite(website);
        }

        return editorialDao.save(editorial);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteEditorial(Integer id) throws InstanceNotFoundException {

    }

}
