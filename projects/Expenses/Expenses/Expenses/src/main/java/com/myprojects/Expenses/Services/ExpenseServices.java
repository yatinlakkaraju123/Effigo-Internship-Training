package com.myprojects.Expenses.Services;

import com.myprojects.Expenses.DTOs.CategoryRequestDTO;
import com.myprojects.Expenses.DTOs.CategoryResponseDTO;
import com.myprojects.Expenses.DTOs.ExpenseRequestDTO;
import com.myprojects.Expenses.DTOs.ExpenseResponseDTO;
import com.myprojects.Expenses.Entities.Category;
import com.myprojects.Expenses.Entities.Expenses;
import com.myprojects.Expenses.Entities.Users;
import com.myprojects.Expenses.Mappers.CategoryMappers;
import com.myprojects.Expenses.Mappers.ExpenseMappers;
import com.myprojects.Expenses.Repositories.CategoryRepository;
import com.myprojects.Expenses.Repositories.ExpenseRepository;
import com.myprojects.Expenses.Repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ExpenseServices {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ExpenseMappers expenseMappers;

    public ResponseEntity<List<ExpenseResponseDTO>> retrieveAllExpenses(){
        try{
            List<Expenses> expensesList = expenseRepository.findAll();
            List<ExpenseResponseDTO> categoryResponseDTOS = expensesList.stream().map(
                    expense -> expenseMappers.ExpensesToExpenseResponse(expense)
            ).toList();
            return new ResponseEntity<>(categoryResponseDTOS, HttpStatus.OK);
        } catch (Exception e) {
            log.info("error in retrieving all expenses:{}", e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<ExpenseResponseDTO> retrieveExpenseById(long id){
        try {
            Optional<Expenses> expense = expenseRepository.findById(id);
            if(expense.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<>(expenseMappers
                        .ExpensesToExpenseResponse(expense.get()),HttpStatus.OK);
            }
        } catch (Exception e) {
            log.info("error in retrieving an expense by id:{}", e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> createExpense(ExpenseRequestDTO expenseRequestDTO,long uid,long cid){
        try {

            Expenses expense = expenseMappers.ExpenseRequestToExpenses(expenseRequestDTO);
            Optional<Users> user = userRepository.findById(uid);
            Optional<Category> category = categoryRepository.findById(cid);
            if(user.isEmpty() || category.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // Save the user first to generate the ID

            expense.setUser(user.get());
            user.get().getExpenses().add(expense);
            expense.setCategory(category.get());
            category.get().getExpensesList().add(expense);



            expenseRepository.save(expense);

            return new ResponseEntity<>("New expense created", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error in expense category: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ExpenseResponseDTO> updateExpense(long id,ExpenseRequestDTO expenseRequestDTO){
        try
        {
            Optional<Expenses> expense = expenseRepository.findById(id);
            Expenses requestedExpense = expenseMappers.ExpenseRequestToExpenses(expenseRequestDTO);
            if(expense.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                if(requestedExpense.getName()!=null) expense.get().setName(requestedExpense.getName());
                if(requestedExpense.getDate()!=null)expense.get().setDate(requestedExpense.getDate());
                if(requestedExpense.getDescription()!=null)expense.get().setDescription(requestedExpense.getDescription());
                if(requestedExpense.getPrice()!=0)expense.get().setPrice(requestedExpense.getPrice());
                return new ResponseEntity<>(expenseMappers
                        .ExpensesToExpenseResponse(expense.get()),HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            log.info("error in update expense:{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<String> deleteExpense(long id){
        try {
            Optional<Expenses> expense = expenseRepository.findById(id);
            if(expense.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            expense.get().setCategory(null);
            expense.get().setUser(null);
            expenseRepository.delete(expense.get());
            return new ResponseEntity<>("expense deleted",HttpStatus.OK);
        } catch (Exception e) {
            log.info("error in delete expense:{}", e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
