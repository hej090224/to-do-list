package com.example.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequest {

    @NotBlank(message = "할 일을 입력해야 합니다.")
    private String task;

    private boolean completed;
}
