package com.hitomi.hop.catalog.model.persistence.jpa;

import com.hitomi.hop.catalog.model.domain.Serie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CustomSerieDaoImpl implements CustomSerieDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Slice<Serie> find(String name, Pageable page) {

        StringBuilder builder = new StringBuilder("FROM Serie s WHERE 1 = 1 ");

        if (name != null) {
            builder.append(" AND LOWER(s.name) LIKE LOWER(:name) ");
        }

        if (page.getSort().isSorted()) {
            builder.append(" ORDER BY ");
            page.getSort().stream().forEach(s -> builder.append(String.format(" s.%s %s ", s.getProperty(), s.getDirection())));
        }

        Query query = entityManager.createQuery(builder.toString())
                .setFirstResult(page.getPageNumber() * page.getPageSize()).setMaxResults(page.getPageSize() + 1);

        if (name != null) {
            query.setParameter("name", '%' + name + '%');
        }

        List<Serie> series = query.getResultList();

        boolean hasNext = series.size() == (page.getPageSize() + 1);
        if (hasNext) {
            series.remove(series.size() - 1);
        }

        return new SliceImpl<>(series, page, hasNext);

    }

}
