package com.techeer.inforplanbackend.domain.user.controller;

import com.techeer.inforplanbackend.domain.user.domain.entity.Users;
import com.techeer.inforplanbackend.domain.user.dto.mapper.UserMapper;
import com.techeer.inforplanbackend.domain.user.dto.request.JwtRequest;
import com.techeer.inforplanbackend.domain.user.dto.request.UserCreateRequestDto;
import com.techeer.inforplanbackend.domain.user.dto.response.JwtResponse;
import com.techeer.inforplanbackend.domain.user.dto.response.UserResponseDto;
import com.techeer.inforplanbackend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class UserController {

    public final UserService userService;

    public final UserMapper userMapper;

    //private final JwtTokenUtil jwtTokenUtil;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@Validated @RequestBody UserCreateRequestDto userRequestDto){

        Users user = userService.create(userRequestDto);

        return userMapper.fromEntity(user);
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id){

        return userService.findById(id);

    }
}
