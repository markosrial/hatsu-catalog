package com.hitomi.catalog.domain.services;

import com.hitomi.catalog.domain.model.Category;

import java.util.List;

public interface CatalogService {

    /**
     * Retrieves all categories
     * @return the category list
     */
    List<Category> findAllCategories();
}
