package com.techeer.inforplanbackend.domain.members_task.dto.Mapper;

import com.techeer.inforplanbackend.domain.members_task.dto.Response.Members_TaskResponse;
import com.techeer.inforplanbackend.domain.members_task.entity.Members_Task;
import com.techeer.inforplanbackend.domain.members_task.repository.Members_TaskRepository;
import com.techeer.inforplanbackend.domain.task.entity.Task;
import com.techeer.inforplanbackend.domain.user.domain.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Members_TaskMapper {

    private final Members_TaskRepository members_taskRepository;

    public Members_Task toEntity(Task task, Users user) {
        return Members_Task.builder()
                .users(user)
                .task(task)
                .build();
    }

    public Members_TaskResponse fromEntity(Members_Task members_task) {
        return Members_TaskResponse.builder()
                .id(members_task.getId())
                .task(members_task.getTask())
                .user(members_task.getUsers())
                .build();
    }
}
