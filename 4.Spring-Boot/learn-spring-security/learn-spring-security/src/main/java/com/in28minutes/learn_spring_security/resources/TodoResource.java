package com.in28minutes.learn_spring_security.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoResource {
    private Logger logger = LoggerFactory.getLogger(getClass());
    List<Todo> todoList = List.of(new Todo("yatin", "Learn AWS"),
            new Todo("yatin", "Learn CEH"),
            new Todo("yatin", "Learn GCP"));
    @GetMapping("todos")
    public List<Todo> retrieveAllTodos(){

        return todoList;

    }
    @GetMapping("/users/{username}/todos")
    public Todo retrieveTodosForASpecificUser(@PathVariable String username){

        return todoList.get(0);

    }
    @PostMapping("/users/{username}/todos")
    public void createTodosForASpecificUser(@PathVariable String username, @RequestBody Todo todo){

    logger.info("Create {} for {}",todo,username);

    }
    record Todo(String username,String description){}

}

