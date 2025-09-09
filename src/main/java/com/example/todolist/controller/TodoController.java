package com.example.todolist.controller;

import com.example.todolist.dto.TodoRequest;
import com.example.todolist.dto.TodoResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.example.todolist.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/load")
    public List<TodoResponse> getAlltodos() {
        return todoService.findAll();
    }

    @PostMapping("/post")
    public TodoResponse addTodo(@Valid @RequestBody TodoRequest todoRequest) {
        return todoService.save(todoRequest);
    }

    @PutMapping("/{id}/edit")
    public TodoResponse updateTodo(@PathVariable Long id, @Valid @RequestBody TodoRequest todoRequest) {
        return todoService.update(id, todoRequest);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
    }
}
