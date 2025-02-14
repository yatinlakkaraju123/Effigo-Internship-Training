package com.myprojects.Expenses.Services;

import com.myprojects.Expenses.DTOs.CategoryRequestDTO;
import com.myprojects.Expenses.DTOs.CategoryResponseDTO;
import com.myprojects.Expenses.DTOs.UserRequestDTO;
import com.myprojects.Expenses.DTOs.UserResponseDTO;
import com.myprojects.Expenses.Entities.*;
import com.myprojects.Expenses.Mappers.CategoryMappers;
import com.myprojects.Expenses.Mappers.UserMappers;
import com.myprojects.Expenses.Repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServices {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    
    @Autowired
    private CategoryMappers categoryMappers;

    public ResponseEntity<List<CategoryResponseDTO>> retrieveAllCategories(){
        try{
            List<Category> categoryList = categoryRepository.findAll();
            List<CategoryResponseDTO> categoryResponseDTOS = categoryList.stream().map(
                    category -> categoryMappers.CategoryToCategoryResponse(category)
            ).toList();
            return new ResponseEntity<>(categoryResponseDTOS, HttpStatus.OK);
        } catch (Exception e) {
            log.info("error in retrieving all categories:{}", e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<CategoryResponseDTO> retrieveCategoryById(long id){
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if(category.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<>(categoryMappers
                        .CategoryToCategoryResponse(category.get()),HttpStatus.OK);
            }
        } catch (Exception e) {
            log.info("error in retrieving a category by id:{}", e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> createCategory(CategoryRequestDTO categoryRequestDTO){
        try {
            if(categoryRepository.existsByName(categoryRequestDTO.getName()))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            Category category = categoryMappers.CategoryRequestToCategory(categoryRequestDTO);


            // Save the user first to generate the ID





            categoryRepository.save(category);

            return new ResponseEntity<>("New category created", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error in creating category: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CategoryResponseDTO> updateCategory(long id,CategoryRequestDTO categoryRequestDTO){
        try
        {
            Optional<Category> category = categoryRepository.findById(id);
            Category requestedCategory = categoryMappers.CategoryRequestToCategory(categoryRequestDTO);
            if(category.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                category.get().setName(requestedCategory.getName());
                return new ResponseEntity<>(categoryMappers
                        .CategoryToCategoryResponse(category.get()),HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            log.info("error in update category:{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<String> deleteCategory(long id){
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if(category.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<Expenses> categoryExpenses = category.get().getExpensesList();
            for(Expenses expense:categoryExpenses){
                expense.setCategory(null);
                expense.setUser(null);
                expenseRepository.delete(expense);
            }
            categoryRepository.delete(category.get());
            return new ResponseEntity<>("category deleted",HttpStatus.OK);
        } catch (Exception e) {
            log.info("error in delete category:{}", e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
