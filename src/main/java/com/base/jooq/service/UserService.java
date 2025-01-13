package com.base.jooq.service;

import com.base.jooq.jooq.dao.UserDao;
import com.base.jooq.jooq.dto.request.user.UserPageReq;
import com.base.jooq.jooq.dto.request.user.UserSaveReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Result;
import org.jooq.generated.tables.User;
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
public class UserService {

    private final UserDao dao;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;


    @Transactional(readOnly = true)
    public List<User> getUsers(UserPageReq req) {
        Result<?> queryResults = dao.getUsers(req);

        // stream 테스트 작성하기

        return queryResults.stream()
                .map(query -> mapper.map(query.into(User.class), User.class)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public User getUser(Long userNo) throws Exception {
        if (Objects.isNull(userNo) || userNo < 1) throw new Exception("This data could not be found.");
        return mapper.map(dao.getUserByNo(userNo), User.class);
    }

    public Boolean signUp(UserSaveReq req) throws Exception {
        if (Objects.isNull(req.getUserId()) || Objects.isNull(req.getUserPwd()))
            throw new Exception("There is no data.");

        req.setUserPwd(passwordEncoder.encode(req.getUserPwd()));
        org.jooq.generated.tables.records.UserRecord userRecord = mapper.map(req, org.jooq.generated.tables.records.UserRecord.class);

        // User pojo return 타입으로 dao 구현하기
        return false;
    }



}
