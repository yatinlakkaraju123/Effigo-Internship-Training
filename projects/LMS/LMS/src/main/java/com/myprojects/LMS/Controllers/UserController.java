package com.myprojects.LMS.Controllers;

import com.myprojects.LMS.DTOs.UserDTO;
import com.myprojects.LMS.Entities.User;
import com.myprojects.LMS.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDTO>> retrieveAllUsers(){
        ResponseEntity<List<UserDTO>> users = userService.retrieveAllUsers();
        return users;
    }
    @PostMapping("/insertUsers")
    public ResponseEntity<String> insertUser(@RequestBody UserDTO userDTO){
        return userService.insertUser(userDTO);
    }
    @PutMapping("update/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id,@RequestBody UserDTO userDTO){
        return userService.updateUser(id,userDTO);
    }
    @DeleteMapping("delete/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
            return userService.deleteUser(id);
    }
}
