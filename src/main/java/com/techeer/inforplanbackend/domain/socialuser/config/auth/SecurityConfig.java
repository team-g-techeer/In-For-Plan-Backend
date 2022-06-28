package com.techeer.inforplanbackend.domain.socialuser.config.auth;

import com.techeer.inforplanbackend.domain.socialuser.entity.SocialRole;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(value = 1)
@AllArgsConstructor
@EnableWebSecurity    //Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
       // super.configure(httpSecurity);
        httpSecurity.csrf().disable()
                .headers().frameOptions().disable()  //h2-console 화면을 사용하기 위해 해당 옵션 disable
                .and()
                    .authorizeRequests()  //url별 권한 관리
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/error/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(SocialRole.USER.name())    // /api/v1/** 은 USER 권한만 접근 가능
                    .anyRequest().authenticated()   //anyRequest: 설정된 값들 이외의 나머지 Url, authenticated : 인증된 사용자
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                     //   .loginPage("/api/v1/hello")
                        .userInfoEndpoint()    //로그인 성공 후 가져올 때의 설정들
                        .userService(customOAuth2UserService);   //리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
    }
}
