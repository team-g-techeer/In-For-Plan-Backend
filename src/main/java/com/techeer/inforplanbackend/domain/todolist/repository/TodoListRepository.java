package com.techeer.inforplanbackend.domain.todolist.repository;

import com.techeer.inforplanbackend.domain.todolist.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
}
