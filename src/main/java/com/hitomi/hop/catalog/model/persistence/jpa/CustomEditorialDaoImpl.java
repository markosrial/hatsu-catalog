package com.hitomi.hop.catalog.model.persistence.jpa;

import com.hitomi.hop.catalog.model.domain.Editorial;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CustomEditorialDaoImpl implements CustomEditorialDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Slice<Editorial> find(String name, String isoCountry, Pageable page) {

        StringBuilder builder = new StringBuilder("FROM Editorial e WHERE 1 = 1 ");

        if (name != null) {
            builder.append(" AND LOWER(e.name) LIKE LOWER(:name) ");
        }

        if (isoCountry != null) {
            builder.append(" AND e.isoCountry = :isoCountry ");
        }

        if (page.getSort().isSorted()) {
            builder.append(" ORDER BY ");
            page.getSort().stream().forEach(s -> builder.append(String.format(" e.%s %s ", s.getProperty(), s.getDirection())));
        }

        Query query = entityManager.createQuery(builder.toString())
                .setFirstResult(page.getPageNumber() * page.getPageSize()).setMaxResults(page.getPageSize() + 1);

        if (name != null) {
            query.setParameter("name", '%' + name + '%');
        }

        if (isoCountry != null) {
            query.setParameter("isoCountry", isoCountry);
        }

        List<Editorial> editorials = query.getResultList();

        boolean hasNext = editorials.size() == (page.getPageSize() + 1);
        if (hasNext) {
            editorials.remove(editorials.size() - 1);
        }

        return new SliceImpl<>(editorials, page, hasNext);

    }

}
