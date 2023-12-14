package com.base.jooq.service;

import com.base.jooq.jooq.bean.tables.pojos.User;
import com.base.jooq.jooq.dao.UserDao;
import com.base.jooq.jooq.dto.request.UserPageReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Result;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserService {

    private final UserDao dao;
    private final ModelMapper mapper;


    public List<User> getUsers(UserPageReq req) {
        Result<?> queryResults = dao.getUsers(req);
        List<User> list = queryResults.stream().map(query -> mapper.map(query.into(User.class), User.class)).collect(Collectors.toList());

        return list;
    }
}
