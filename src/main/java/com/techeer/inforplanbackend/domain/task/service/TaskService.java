package com.techeer.inforplanbackend.domain.task.service;

import com.techeer.inforplanbackend.domain.members_task.dto.Mapper.Members_TaskMapper;
import com.techeer.inforplanbackend.domain.members_task.dto.Response.Members_TaskResponse;
import com.techeer.inforplanbackend.domain.members_task.entity.Members_Task;
import com.techeer.inforplanbackend.domain.members_task.repository.Members_TaskRepository;
import com.techeer.inforplanbackend.domain.task.dto.mapper.TaskMapper;
import com.techeer.inforplanbackend.domain.task.dto.request.TaskRequestDto;
import com.techeer.inforplanbackend.domain.task.dto.response.TaskResponseDto;
import com.techeer.inforplanbackend.domain.task.entity.Task;
import com.techeer.inforplanbackend.domain.task.repository.TaskRepository;
import com.techeer.inforplanbackend.domain.user.domain.entity.Users;
import com.techeer.inforplanbackend.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@AllArgsConstructor
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    private TaskMapper taskMapper;
    private Members_Task members_task;
    private final UserRepository userRepository;
    private final Members_TaskMapper members_taskMapper;
    private final Members_TaskRepository members_taskRepository;

    @Transactional
    public Task create(TaskRequestDto taskRequestDto, String email) {
        Users user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 없습니다."));
        Task task = taskRepository.save(taskMapper.toEntity(taskRequestDto));
        members_taskRepository.save(members_taskMapper.toEntity(task, user));
        return task;
    }

    @Transactional
    public List<Task> all(String email) {
        Users user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 없습니다."));
        List<Members_Task> memberlist = members_taskRepository.findMembers_TasksByUsers(user);
        List<Task> tasklist = new ArrayList<Task>();
        for (int i = 0; i < memberlist.size(); i++) {
            tasklist.add(memberlist.get(i).getTask());
        }
        return tasklist;
    }

    @Transactional
    public void deleteTask(Long task_id, String email) {
        Users user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 없습니다."));
        Task task = taskRepository.findById(task_id).orElseThrow(() ->
                new IllegalArgumentException("해당 Task가 없습니다."));
        members_task = members_taskRepository.findMembers_TaskByTask(task);
        members_taskRepository.delete(members_task);
        taskRepository.deleteById(task_id);
    }

    @Transactional
    public TaskResponseDto findById(Long task_id) {
        Task task = taskRepository.findById(task_id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 task가 없습니다."));
        return taskMapper.fromEntity(task);
    }

    @Transactional
    public void update(Long task_id, TaskRequestDto taskRequestDto, String email) {
        Users user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 없습니다."));
        Task task = taskRepository.findById(task_id).orElseThrow(() -> new IllegalArgumentException("해당하는 task가 없습니다."));

        task.update(taskRequestDto.getList_id(), taskRequestDto.getDescription(), taskRequestDto.getStart_date(),
                taskRequestDto.getEnd_date(), taskRequestDto.getFile_url(), taskRequestDto.getTask_title());

        members_task.update(user, task);
    }

    @Transactional
    public ZonedDateTime KoreaToAmericaTime(String KoreaTimeStart) //time yyyy,mm,dd,hh,mm , Korea Timezone to America TimeZone(LA), korea time is 16 hours ahead of LA
    {
        StringTokenizer st = new StringTokenizer(KoreaTimeStart, ",");
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        int hour = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());

        ZonedDateTime startTime = Year.of(year).atMonth(month).atDay(day).atTime(hour, min).atZone(ZoneId.of("Asia/Seoul"));
        System.out.println(startTime);


        ZonedDateTime AmericaStartTime = startTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));

        return AmericaStartTime;
    }

    public ZonedDateTime AmericaToKorea(String AmericaTimeStart) {
        StringTokenizer st = new StringTokenizer(AmericaTimeStart, ",");
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        int hour = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());

        ZonedDateTime startTime = Year.of(year).atMonth(month).atDay(day).atTime(hour, min).atZone(ZoneId.of("America/Los_Angeles"));
        ZonedDateTime koreaStartTime = startTime.withZoneSameInstant(ZoneId.of("Asia/Seoul"));

        return koreaStartTime;
    }


}
