package com.hitomi.catalog.domain.model;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryDao extends PagingAndSortingRepository<Category, Short> {}
