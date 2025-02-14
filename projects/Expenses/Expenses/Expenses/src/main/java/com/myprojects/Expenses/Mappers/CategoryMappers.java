package com.myprojects.Expenses.Mappers;

import com.myprojects.Expenses.DTOs.CategoryRequestDTO;
import com.myprojects.Expenses.DTOs.CategoryResponseDTO;
import com.myprojects.Expenses.Entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMappers {
    @Mapping(source = "categoryId",target = "id")
    Category CategoryRequestToCategory(CategoryRequestDTO categoryRequestDTO);
    @Mapping(target = "categoryId",source = "id")
    CategoryResponseDTO CategoryToCategoryResponse(Category category);
}
