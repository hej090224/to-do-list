package controller;


import dto.TodoRequest;
import dto.TodoResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoResponse> getAlltodos() {
        return todoService.findAll();
    }

    @PostMapping
    public TodoResponse addTodo(@Valid @RequestBody TodoRequest todoRequest) {
        return todoService.save(todoRequest);
    }

    @PutMapping("/{id}")
    public TodoResponse updateTodo(@PathVariable Long id, @Valid @RequestBody TodoRequest todoRequest) {
        return todoService.update(id, todoRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
    }
}
