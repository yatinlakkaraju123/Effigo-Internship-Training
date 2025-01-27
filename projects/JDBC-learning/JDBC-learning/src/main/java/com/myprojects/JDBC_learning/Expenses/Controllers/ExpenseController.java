package com.myprojects.JDBC_learning.Expenses.Controllers;

import com.myprojects.JDBC_learning.Expenses.Expense;
import com.myprojects.JDBC_learning.Expenses.Repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ExpenseController {
    private ExpenseRepository expenseRepository;

    public ExpenseController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @GetMapping("/expenses")
    public ResponseEntity<String> getAllExpenses(){
        try{
            List<Expense> expenses = expenseRepository.getAllExpenses();
            return new ResponseEntity<>(expenses.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
