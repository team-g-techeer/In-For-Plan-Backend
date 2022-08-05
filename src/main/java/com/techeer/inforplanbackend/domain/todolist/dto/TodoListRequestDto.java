package com.techeer.inforplanbackend.domain.todolist.dto;

import com.techeer.inforplanbackend.domain.user.domain.entity.Users;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TodoListRequestDto {
    private Users userId;
    private boolean isCheck;
    private String todoTitle;
}
