package com.techeer.inforplanbackend.domain.socialuser.config.auth;

import com.techeer.inforplanbackend.domain.socialuser.entity.SocialUsers;
import com.techeer.inforplanbackend.domain.socialuser.repository.SocialUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final SocialUserRepository socialUserRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //OAuth2 google ID
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        SocialUsers socialUsers = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(socialUsers));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(socialUsers.getRoleKey())),
                attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    private SocialUsers saveOrUpdate(OAuthAttributes attributes) {
        SocialUsers socialUsers = socialUserRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return socialUserRepository.save(socialUsers);
    }
}
