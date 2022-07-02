package com.techeer.inforplanbackend.domain.socialuser.config.auth;

import com.techeer.inforplanbackend.domain.socialuser.entity.SocialUsers;
import lombok.Getter;
import org.hibernate.Session;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(SocialUsers socialUsers) {
        this.name = socialUsers.getName();
        this.email = socialUsers.getEmail();
        this.picture = socialUsers.getPicture();
    }
}
