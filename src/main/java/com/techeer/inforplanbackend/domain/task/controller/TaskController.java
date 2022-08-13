package com.techeer.inforplanbackend.domain.task.controller;


import com.techeer.inforplanbackend.domain.task.dto.mapper.TaskMapper;
import com.techeer.inforplanbackend.domain.task.dto.request.TaskRequestDto;
import com.techeer.inforplanbackend.domain.task.dto.response.TaskResponseDto;
import com.techeer.inforplanbackend.domain.task.entity.Task;
import com.techeer.inforplanbackend.domain.task.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1")
public class TaskController {
    public final TaskService taskService;
    public final TaskMapper taskMapper;

    @PostMapping("/tasks")
            public TaskResponseDto create(@RequestBody TaskRequestDto taskRequestDto) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String email = ((User) auth.getPrincipal()).getUsername();
                Task task = taskService.create(taskRequestDto, email);
                return taskMapper.fromEntity(task);
            }

            @GetMapping
            public List<Task> all() {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String email = ((User) auth.getPrincipal()).getUsername();
                List<Task> task = taskService.all(email);
                return task;
            }

            @DeleteMapping("/tasks/{task_id}")
            public String delete(@PathVariable Long task_id) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String email = ((User) auth.getPrincipal()).getUsername();
                taskService.deleteTask(task_id, email);
                return "삭제된 task의 id : " + task_id;
            }

            @GetMapping("/tasks/{task_id}")
            public TaskResponseDto find(@PathVariable Long task_id) {
                return taskService.findById(task_id);
            }

            @PutMapping("/tasks/{task_id}")
            public TaskResponseDto update(@PathVariable Long task_id, @RequestBody TaskRequestDto taskRequestDto) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String email = ((User) auth.getPrincipal()).getUsername();
        taskService.update(task_id, taskRequestDto, email);
        return taskService.findById(task_id);
    }
    @GetMapping("/tasks/time_trans")
    public ZonedDateTime time_transfer(String time, String nation)
    {
        if(nation.equals("korea"))
        {
             return taskService.KoreaToAmericaTime(time);
        }else
        {
            return taskService.AmericaToKorea(time);
        }
    }

}
