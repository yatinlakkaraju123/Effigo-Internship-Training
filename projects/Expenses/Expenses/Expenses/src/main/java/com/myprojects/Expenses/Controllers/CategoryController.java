package com.myprojects.Expenses.Controllers;

import com.myprojects.Expenses.DTOs.CategoryRequestDTO;
import com.myprojects.Expenses.DTOs.CategoryResponseDTO;
import com.myprojects.Expenses.DTOs.UserRequestDTO;
import com.myprojects.Expenses.DTOs.UserResponseDTO;
import com.myprojects.Expenses.Services.CategoryServices;
import com.myprojects.Expenses.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category/")
public class CategoryController {
    @Autowired
    private CategoryServices categoryServices;
    @GetMapping("retrieveAll")
    public ResponseEntity<List<CategoryResponseDTO>> retrieveAllCategories(){
        return categoryServices.retrieveAllCategories();
    }
    @GetMapping("retrieve/{id}")
    public ResponseEntity<CategoryResponseDTO> retrieveCategoryById(@PathVariable long id){
        return categoryServices.retrieveCategoryById(id);
    }
    @PostMapping("create")
    public ResponseEntity<String> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        return categoryServices.createCategory(categoryRequestDTO);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable long id
            ,@RequestBody CategoryRequestDTO categoryRequestDTO){
        return categoryServices.updateCategory(id,categoryRequestDTO);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id){
        return categoryServices.deleteCategory(id);
    }
}
