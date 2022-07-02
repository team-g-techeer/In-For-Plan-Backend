package com.techeer.inforplanbackend.domain.socialuser.entity;

import com.techeer.inforplanbackend.global.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class SocialUsers extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialRole role;

    @Builder
    public SocialUsers(String name, String email, String picture, SocialRole role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public SocialUsers update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
