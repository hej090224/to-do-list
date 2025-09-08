package service;

import dto.TodoRequest;
import dto.TodoResponse;
import entity.Todo;
import org.springframework.stereotype.Service;
import repository.TodoRepository;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponse> findAll() {
        return todoRepository.findAll()
                .stream()
                .map(todo -> new TodoResponse(todo.getId(), todo.getTask(), todo.isCompleted()))
                .toList();
    }

    public TodoResponse save(TodoRequest request) {
        Todo todo = Todo.builder()
                .task(request.getTask())
                .completed(request.isCompleted())
                .build();
        Todo saved = todoRepository.save(todo);
        return new TodoResponse(saved.getId(), saved.getTask(), saved.isCompleted());
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    public TodoResponse update(Long id,TodoRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 Todo가 존재하지 않습니다."));

        todo.setTask(request.getTask());
        todo.setCompleted(request.isCompleted());

        Todo updated = todoRepository.save(todo);
        return new TodoResponse(updated.getId(), updated.getTask(), updated.isCompleted());
    }
}
