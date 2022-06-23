package com.techeer.inforplanbackend.domain.members_task.entity;

import com.techeer.inforplanbackend.domain.task.entity.Task;
import com.techeer.inforplanbackend.domain.user.domain.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@org.springframework.stereotype.Component
public class Members_Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Builder
    public Members_Task(Long id, Users users, Task task) {
        this.id = id;
        this.users = users;
        this.task = task;
    }

    public void update(Users users, Task task) {
        this.users = users;
        this.task = task;
    }
}
