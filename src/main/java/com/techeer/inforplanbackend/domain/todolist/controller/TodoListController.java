package com.techeer.inforplanbackend.domain.todolist.controller;

import com.techeer.inforplanbackend.domain.todolist.dto.TodoListMapper;
import com.techeer.inforplanbackend.domain.todolist.dto.TodoListRequestDto;
import com.techeer.inforplanbackend.domain.todolist.dto.TodoListResponseDto;
import com.techeer.inforplanbackend.domain.todolist.entity.TodoList;
import com.techeer.inforplanbackend.domain.todolist.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/todo")
public class TodoListController {
    public final TodoListService todoListService;
    public final TodoListMapper todoListMapper;

    @GetMapping
    public List<TodoList> getAll() {
        return todoListService.getAll();
    }

    @PostMapping
    public TodoListResponseDto save(@RequestBody TodoListRequestDto todoListRequestDto) {
        TodoList todoList = todoListService.save(todoListRequestDto);
        return todoListMapper.fromEntity(todoList);
    }
}
