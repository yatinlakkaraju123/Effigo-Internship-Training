package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	private static int todoCount = 0;
	static {
		todos.add(new Todo(++todoCount,"yatin","Learn python",
				LocalDate.now().plusYears(1),false));
		todos.add(new Todo(++todoCount,"yatin","Learn java",
				LocalDate.now().plusYears(2),false));
		todos.add(new Todo(++todoCount,"yatin","Learn C",
				LocalDate.now().plusYears(3),false));
		todos.add(new Todo(++todoCount,"yatin","Learn JS",
				LocalDate.now().plusYears(4),false));
		
		
	}
	public List<Todo> findByUserName(String Username)
	{Predicate<? super Todo> predicate = todo ->todo.getUsername().equalsIgnoreCase(Username);
	
	
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String username,String description,LocalDate date,boolean done)
	{
		Todo todo = new Todo(++todoCount,username,description,date,done);
		todos.add(todo);
	}
	public void deleteTodo(int id)
	{
		Predicate<? super Todo> predicate = todo ->todo.getId() == id;
		todos.removeIf(predicate);
	}
	public Todo findById(int id)
	{
		Predicate<? super Todo> predicate = todo ->todo.getId() == id;
		return todos.stream().filter(predicate).findFirst().get();
	}
	public void updateById(Todo todo)
	{
		deleteTodo(todo.getId());
		todos.add(todo);
	}
}
