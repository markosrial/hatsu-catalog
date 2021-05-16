package com.hitomi.catalog.rest.dto.category;

import com.hitomi.catalog.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    /**
     * Maps a category to a category dto
     * @param category the category entity
     * @return the category dto
     */
    CategoryDto toCategoryDto(Category category);

    /**
     * Converts a list of categories to a list of categories dto
     * @param categories the categories list
     * @return the categories dto list
     */
    List<CategoryDto> toCategoriesDto(List<Category> categories);
}
