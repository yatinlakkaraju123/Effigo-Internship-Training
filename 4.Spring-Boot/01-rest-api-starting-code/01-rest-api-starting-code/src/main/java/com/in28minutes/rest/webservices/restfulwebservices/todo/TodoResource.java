package com.in28minutes.rest.webservices.restfulwebservices.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
public class TodoResource {
    private TodoService todoService;

    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username)
    {
            return todoService.findByUsername(username);
    }
    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveSingleTodo(@PathVariable String username,@PathVariable int id)
    {
        return todoService.findById(id);
    }
    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id)
    {
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> updateTodo(@PathVariable String username,
                                           @PathVariable int id,@RequestBody Todo todo)
    {
        System.out.println("Received Todo: " + todo);

        todoService.updateTodo(todo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username,@RequestBody Todo todo)
    {
        Todo createdTodo = todoService.addTodo(username,todo.getDescription()
        ,todo.getTargetDate(),todo.isDone());
        return createdTodo;

    }
}
