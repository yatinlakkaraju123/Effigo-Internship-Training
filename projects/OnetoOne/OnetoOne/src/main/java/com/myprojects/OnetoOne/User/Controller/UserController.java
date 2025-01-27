package com.myprojects.OnetoOne.User.Controller;

import com.myprojects.OnetoOne.User.Model.User;
import com.myprojects.OnetoOne.User.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>>  getAllUsers()
    {
        return userService.getAllUsers();
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<User> retrieveUserById(@PathVariable int id)
    {

        return userService.getUserById(id);


    }

    @PostMapping("/users")
    public ResponseEntity<String> insertUser(@RequestBody User user){
        return userService.insertUser(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User user)
    {
        return userService.updateUser(id,user);
    }

    @DeleteMapping("/users/{id}")
        public ResponseEntity<String> deleteUsers(@PathVariable int id){
            return userService.deleteUser(id);
         }
}
