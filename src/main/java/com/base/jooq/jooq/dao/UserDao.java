package com.base.jooq.jooq.dao;

import com.base.jooq.jooq.bean.tables.pojos.User;
import com.base.jooq.jooq.dto.request.user.UserPageReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

import static com.base.jooq.jooq.bean.Tables.USER;


@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDao extends BaseDao {

    private final com.base.jooq.jooq.bean.tables.User user = USER;
    private DSLContext query;

    public UserDao(DSLContext query) {
        this.query = query;
    }


    private Condition isIncludes(UserPageReq req) {
        if (Objects.isNull(req.getName())) return DSL.condition(true);

        return user.NAME.contains(req.getName())
                .and(user.IS_ACTIVE.isTrue());
    }

    public Result<?> getUsers(UserPageReq req) {
        return query.select()
                .from(user)
                .where(this.isIncludes(req))
                .and(user.IS_ACTIVE.isTrue())
                .fetch();
    }

    public Optional<User> getUserByNo(Long userNo) {
        return query.select()
                .from(user)
                .where(user.USER_NO
                .eq(userNo))
                .fetchOptionalInto(User.class);
    }
}
