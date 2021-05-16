package com.hitomi.catalog.rest.controller;

import com.hitomi.catalog.domain.services.CatalogService;
import com.hitomi.catalog.rest.dto.category.CategoryDto;
import com.hitomi.catalog.rest.dto.category.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
@PreAuthorize("permitAll()")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/categories")
    @PreAuthorize("permitAll()")
    public List<CategoryDto> getCategories() {
        return CategoryMapper.INSTANCE.toCategoriesDto(catalogService.findAllCategories());
    }

}
