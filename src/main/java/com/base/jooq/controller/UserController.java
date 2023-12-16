package com.base.jooq.controller;

import com.base.jooq.jooq.bean.tables.pojos.User;
import com.base.jooq.jooq.dto.request.user.UserPageReq;
import com.base.jooq.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService service;


    @GetMapping
    public ResponseEntity<List<User>> getUsers(@ModelAttribute UserPageReq req) {
        return ResponseEntity.ok().body(service.getUsers(req));
    }
}
