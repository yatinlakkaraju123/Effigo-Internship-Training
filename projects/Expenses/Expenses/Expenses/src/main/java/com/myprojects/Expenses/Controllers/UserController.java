package com.myprojects.Expenses.Controllers;

import com.myprojects.Expenses.DTOs.DateRangeRequestDTO;
import com.myprojects.Expenses.DTOs.ExpenseResponseDTO;
import com.myprojects.Expenses.DTOs.UserRequestDTO;
import com.myprojects.Expenses.DTOs.UserResponseDTO;
import com.myprojects.Expenses.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users/")
public class UserController {
    @Autowired
    private UserServices userServices;
    @GetMapping("retrieveAll")
    public ResponseEntity<List<UserResponseDTO>> retrieveAllUsers(){
        return userServices.retrieveAllUsers();
    }
    @GetMapping("retrieve/{id}")
    public ResponseEntity<UserResponseDTO> retrieveUserById(@PathVariable long id){
        return userServices.retrieveUserById(id);
    }
    @GetMapping("retrieveExpenses/{id}")
    public ResponseEntity<List<ExpenseResponseDTO>> retrieveExpensesByUserId(@PathVariable long id){
        return userServices.retrieveExpensesByUserId(id);
    }
    @GetMapping("retrieveExpenses/user/{uid}/category/{cid}")
    public ResponseEntity<List<ExpenseResponseDTO>> retrieveExpensesByUserAndCategory(@PathVariable long uid,
                                                                                      @PathVariable long cid)
    {
        return  userServices.retrieveExpensesByUserAndCategory(uid,cid);

    }
    @GetMapping("retrieveExpenses/user/{uid}/date")
    public ResponseEntity<List<ExpenseResponseDTO>> retrieveExpensesByDateRange(@PathVariable long uid
            , @RequestBody DateRangeRequestDTO dateRangeRequestDTO){
        return userServices.retrieveExpensesByDateRange(uid,dateRangeRequestDTO);
    }
    @PostMapping("create")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestDTO userRequestDTO){
        return userServices.registerUser(userRequestDTO);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable long id
            ,@RequestBody UserRequestDTO userRequestDTO){
        return userServices.updateUser(id,userRequestDTO);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        return userServices.deleteUser(id);
    }
}
