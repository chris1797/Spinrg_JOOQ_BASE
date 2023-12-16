package com.base.jooq.service;

import com.base.jooq.jooq.bean.tables.pojos.User;
import com.base.jooq.jooq.bean.tables.records.UserRecord;
import com.base.jooq.jooq.dao.UserDao;
import com.base.jooq.jooq.dto.request.user.UserPageReq;
import com.base.jooq.jooq.dto.request.user.UserSaveReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Result;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserService {

    private final UserDao dao;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;


    public List<User> getUsers(UserPageReq req) {
        Result<?> queryResults = dao.getUsers(req);

        // stream 테스트 작성하기
        List<User> list = queryResults.stream().map(query -> mapper.map(query.into(User.class), User.class)).collect(Collectors.toList());

        return list;
    }

    public Boolean save(UserSaveReq req) throws Exception {
        if (Objects.isNull(req.getUserId()) || Objects.isNull(req.getUserPwd()))
            throw new Exception("There is no data.");

        req.setUserPwd(passwordEncoder.encode(req.getUserPwd()));
        UserRecord userRecord = mapper.map(req, UserRecord.class);

        // User pojo return 타입으로 dao 구현하기
        return false;
    }
}
