package com.base.jooq.controller;

import com.base.jooq.jooq.dto.request.user.UserPageReq;
import com.base.jooq.jooq.dto.request.user.UserSaveReq;
import com.base.jooq.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jooq.generated.tables.User;
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
        return ResponseEntity.ok(service.getUsers(req));
    }

    @GetMapping("/{userNo:[\\d]+}")
    public ResponseEntity<User> getUser(@PathVariable Long userNo) throws Exception {
        return ResponseEntity.ok(service.getUser(userNo));
    }

    @PostMapping("/sign/up")
    public ResponseEntity<Boolean> signUp(@RequestBody UserSaveReq req) throws Exception {
        return ResponseEntity.ok().body(service.signUp(req));
    }
}
