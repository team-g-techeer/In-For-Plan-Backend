package com.techeer.inforplanbackend.domain.members_task.dto.Response;

import com.techeer.inforplanbackend.domain.task.entity.Task;
import com.techeer.inforplanbackend.domain.user.domain.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Members_TaskResponse {
    public Long id;
    public Task task;
    public Users user;
}
