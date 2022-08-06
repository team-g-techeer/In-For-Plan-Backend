package com.techeer.inforplanbackend.domain.todolist.dto;

import com.techeer.inforplanbackend.domain.user.domain.entity.Users;
import lombok.Builder;

@Builder
public class TodoListResponseDto {
    public Users userId;
    public boolean isCheck;
    public String todoTitle;
}
