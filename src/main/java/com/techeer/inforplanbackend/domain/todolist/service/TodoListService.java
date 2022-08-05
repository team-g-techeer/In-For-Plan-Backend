package com.techeer.inforplanbackend.domain.todolist.service;

import com.techeer.inforplanbackend.domain.todolist.dto.TodoListMapper;
import com.techeer.inforplanbackend.domain.todolist.dto.TodoListRequestDto;
import com.techeer.inforplanbackend.domain.todolist.dto.TodoListResponseDto;
import com.techeer.inforplanbackend.domain.todolist.entity.TodoList;
import com.techeer.inforplanbackend.domain.todolist.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoListService {
    private final TodoListRepository todoListRepository;
    private final TodoListMapper todoListMapper;

    @Transactional
    public TodoList save(TodoListRequestDto todoListRequestDto) {
        TodoList todoList = todoListRepository.save(todoListMapper.toEntity(todoListRequestDto));
        return todoList;
    }

    @Transactional
    public List<TodoList> getAll() {
        return todoListRepository.findAll();
    }

}
