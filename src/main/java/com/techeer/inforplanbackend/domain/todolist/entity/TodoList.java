package com.techeer.inforplanbackend.domain.todolist.entity;

import com.techeer.inforplanbackend.domain.user.domain.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoListId;

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Users userId;

    private boolean isCheck;

    private String todoTitle;

    @Builder
    public TodoList(Users userId, boolean isCheck, String todoTitle) {
        this.userId = userId;
        this.isCheck = isCheck;
        this.todoTitle = todoTitle;
    }

    public void update(boolean isCheck, String todoTitle) {
        this.todoTitle = todoTitle;
        this.isCheck = isCheck;
    }
}
