package com.example.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TodoResponse {
    private Long id;

    private String task;

    private boolean completed;
}
