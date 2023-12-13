package com.base.jooq.service;

import com.base.jooq.jooq.dao.UserDao;
import com.base.jooq.jooq.dto.request.UserPageReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserService {

    private final UserDao dao;

    public void getUsers(UserPageReq req) {}
}
