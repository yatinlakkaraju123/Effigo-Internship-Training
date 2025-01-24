package com.in28minutes.rest.webservices.restfulwebservices.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.in28minutes.rest.webservices.restfulwebservices.todo.repository.TodoRepository;

import java.util.List;

@RestController
public class TodoJpaResource {
    private TodoService todoService;
    private TodoRepository todoRepository;
    public TodoJpaResource(TodoService todoService,TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username)
    {
            return todoRepository.findByUsername(username);
    }
    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveSingleTodo(@PathVariable String username,@PathVariable int id)
    {
       // return todoService.findById(id);
        return todoRepository.findById(id).get();

    }
    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id)
    {
        //todoService.deleteById(id);
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> updateTodo(@PathVariable String username,
                                           @PathVariable int id,@RequestBody Todo todo)
    {
        //System.out.println("Received Todo: " + todo);

        //todoService.updateTodo(todo);
    	todoRepository.save(todo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username,@RequestBody Todo todo)
    {	todo.setUsername(username);
    	todo.setId(null);
    	return todoRepository.save(todo);
       // Todo createdTodo = todoService.addTodo(username,todo.getDescription()
       // ,todo.getTargetDate(),todo.isDone());
       // return createdTodo;

    }
}
