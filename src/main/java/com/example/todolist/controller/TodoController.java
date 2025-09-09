package com.example.todolist.controller;

import com.example.todolist.dto.TodoRequestDto;
import com.example.todolist.dto.TodoResponseDto;
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
    public List<TodoResponseDto> getAlltodos() {
        return todoService.findAll();
    }

    @PostMapping("/post")
    public TodoResponseDto addTodo(@Valid @RequestBody TodoRequestDto todoRequestdto) {
        return todoService.save(todoRequestdto);
    }

    @PutMapping("/{id}/edit")
    public TodoResponseDto updateTodo(@PathVariable Long id, @Valid @RequestBody TodoRequestDto todoRequestdto) {
        return todoService.update(id, todoRequestdto);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
    }
}
