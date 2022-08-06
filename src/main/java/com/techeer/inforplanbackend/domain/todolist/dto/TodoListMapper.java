package com.techeer.inforplanbackend.domain.todolist.dto;

import com.techeer.inforplanbackend.domain.todolist.entity.TodoList;
import com.techeer.inforplanbackend.domain.todolist.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TodoListMapper {
    private final TodoListRepository todoListRepository;

    public TodoList toEntity(TodoListRequestDto todoListRequestDto) {
        return TodoList.builder()
                .userId(todoListRequestDto.getUserId())
                .isCheck(todoListRequestDto.isCheck())
                .todoTitle(todoListRequestDto.getTodoTitle())
                .build();
    }

    public TodoListResponseDto fromEntity(TodoList todoList) {
        return TodoListResponseDto.builder()
                .isCheck(todoList.isCheck())
                .todoTitle(todoList.getTodoTitle())
                .build();
    }
}
