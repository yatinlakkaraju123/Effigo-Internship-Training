package com.myprojects.JDBC_learning.User.Controllers;

import com.myprojects.JDBC_learning.User.Repository.UserRepository;
import com.myprojects.JDBC_learning.User.Services.UserService;
import com.myprojects.JDBC_learning.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserRepository userRepository;
    private UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userRepository.selectAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<String> insertUser(@RequestBody User user) {
        try {
            userRepository.addUser(user);
            return new ResponseEntity<>("new user is created", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("The user is not created. There is an error",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        try {
            User getUser = userRepository.selectUserById(id);
            if (getUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(getUser, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> UpdateUsers(@PathVariable int id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUsers(@PathVariable int id)
    {
        return userService.deleteUser(id);
    }
}
