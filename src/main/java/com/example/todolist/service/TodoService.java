package com.example.todolist.service;

import com.example.todolist.dto.TodoRequestDto;
import com.example.todolist.dto.TodoResponseDto;
import com.example.todolist.entity.Todo;
import org.springframework.stereotype.Service;
import com.example.todolist.repository.TodoRepository;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponseDto> findAll() {
        return todoRepository.findAll()
                .stream()
                .map(todo -> new TodoResponseDto(todo.getId(), todo.getTask(), todo.isCompleted()))
                .toList();
    }

    public TodoResponseDto save(TodoRequestDto request) {
        Todo todo = Todo.builder()
                .task(request.getTask())
                .completed(request.isCompleted())
                .build();
        Todo saved = todoRepository.save(todo);
        return new TodoResponseDto(saved.getId(), saved.getTask(), saved.isCompleted());
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    public TodoResponseDto update(Long id, TodoRequestDto request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 Todo가 존재하지 않습니다."));

        todo.setTask(request.getTask());
        todo.setCompleted(request.isCompleted());

        Todo updated = todoRepository.save(todo);
        return new TodoResponseDto(updated.getId(), updated.getTask(), updated.isCompleted());
    }
}
