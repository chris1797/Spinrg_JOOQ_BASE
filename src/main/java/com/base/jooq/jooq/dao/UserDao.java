package com.base.jooq.jooq.dao;

import com.base.jooq.jooq.bean.Tables;
import com.base.jooq.jooq.dto.request.BoardPageReq;
import com.base.jooq.jooq.dto.request.UserPageReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDao extends BaseDao {

    private final DSLContext query;


    private Condition isIncludes(UserPageReq req) {
        if (Objects.isNull(req.getName())) return DSL.condition(true);

        return Tables.TBLUSER.NAME.contains(req.getName())
                .and(Tables.TBLUSER.ISACTIVE.isTrue());
    }
}
