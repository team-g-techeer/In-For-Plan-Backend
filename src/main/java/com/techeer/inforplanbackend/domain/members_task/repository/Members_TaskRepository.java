package com.techeer.inforplanbackend.domain.members_task.repository;

import com.techeer.inforplanbackend.domain.members_task.entity.Members_Task;
import com.techeer.inforplanbackend.domain.task.entity.Task;
import com.techeer.inforplanbackend.domain.user.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Members_TaskRepository extends JpaRepository<Members_Task, Long> {
    Members_Task findMembers_TaskByTask(Task task);

    List<Members_Task> findMembers_TasksByUsers(Users user);

}
