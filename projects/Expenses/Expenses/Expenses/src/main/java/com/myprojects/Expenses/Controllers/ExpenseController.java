package com.myprojects.Expenses.Controllers;

import com.myprojects.Expenses.DTOs.CategoryRequestDTO;
import com.myprojects.Expenses.DTOs.CategoryResponseDTO;
import com.myprojects.Expenses.DTOs.ExpenseRequestDTO;
import com.myprojects.Expenses.DTOs.ExpenseResponseDTO;
import com.myprojects.Expenses.Services.CategoryServices;
import com.myprojects.Expenses.Services.ExpenseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses/")
public class ExpenseController {
    @Autowired
    private ExpenseServices expenseServices;
    @GetMapping("retrieveAll")
    public ResponseEntity<List<ExpenseResponseDTO>> retrieveAllExpenses(){
        return expenseServices.retrieveAllExpenses();
    }
    @GetMapping("retrieve/{id}")
    public ResponseEntity<ExpenseResponseDTO> retrieveExpenseById(@PathVariable long id){
        return expenseServices.retrieveExpenseById(id);
    }
    @PostMapping("create/user/{uid}/category/{cid}")
    public ResponseEntity<String> createExpense(@RequestBody ExpenseRequestDTO expenseRequestDTO
            ,@PathVariable int uid,@PathVariable int cid){
        return expenseServices.createExpense(expenseRequestDTO,uid,cid);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<ExpenseResponseDTO> updateExpense(@PathVariable long id
            ,@RequestBody ExpenseRequestDTO expenseRequestDTO){
        return expenseServices.updateExpense(id,expenseRequestDTO);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable long id){
        return expenseServices.deleteExpense(id);
    }
}
