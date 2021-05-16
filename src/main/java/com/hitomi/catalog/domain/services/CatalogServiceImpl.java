package com.hitomi.catalog.domain.services;

import com.hitomi.catalog.domain.model.Category;
import com.hitomi.catalog.domain.model.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CategoryDao categoryDao;

    /**
     * {@inheritdoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Category> findAllCategories() {
        List<Category> categoriesAsList = new ArrayList<>();
        categoryDao.findAll(Sort.by(Sort.Direction.ASC, "name")).forEach(categoriesAsList::add);
        return  categoriesAsList;
    }
}
