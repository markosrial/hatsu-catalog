package com.hitomi.hop.catalog.model.persistence.jpa;

import com.hitomi.hop.catalog.model.domain.Mangaka;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CustomMangakaDaoImpl implements CustomMangakaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Slice<Mangaka> find(String name, String isoCountry, Pageable page) {

        StringBuilder builder = new StringBuilder("FROM Mangaka m WHERE 1 = 1 ");

        if (name != null) {
            builder.append(" AND LOWER(m.name) LIKE LOWER(:name) ");
        }

        if (isoCountry != null) {
            builder.append(" AND m.isoCountry = :isoCountry ");
        }

        if (page.getSort().isSorted()) {
            builder.append(" ORDER BY ");
            page.getSort().stream().forEach(s -> builder.append(String.format(" m.%s %s ", s.getProperty(), s.getDirection())));
        }

        Query query = entityManager.createQuery(builder.toString())
                .setFirstResult(page.getPageNumber() * page.getPageSize()).setMaxResults(page.getPageSize() + 1);

        if (name != null) {
            query.setParameter("name", '%' + name + '%');
        }

        if (isoCountry != null) {
            query.setParameter("isoCountry", isoCountry);
        }

        List<Mangaka> mangakas = query.getResultList();

        boolean hasNext = mangakas.size() == (page.getPageSize() + 1);
        if (hasNext) {
            mangakas.remove(mangakas.size() - 1);
        }

        return new SliceImpl<>(mangakas, page, hasNext);

    }

}
