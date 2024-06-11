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

import static com.base.jooq.jooq.bean.Tables.COMMENT;
import static com.base.jooq.jooq.bean.Tables.USER;
import static org.jooq.impl.DSL.select;


@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDao extends BaseDao {

    private final DSLContext query;


    private Condition isIncludes(UserPageReq req) {
        if (Objects.isNull(req.getName())) return DSL.condition(true);

        return USER.NAME.contains(req.getName())
                .and(USER.IS_ACTIVE.isTrue());
    }

    public Result<?> getUsers(UserPageReq req) {
        return query.select(DSL.multiset(
                select(COMMENT.COMMENT_NO, COMMENT.CONTENT)
                        .from(COMMENT).join(USER).on(COMMENT.USER_NO.eq(USER.USER_NO)
                )).as("comments"))
                .from(USER)
                .where(this.isIncludes(req))
                .and(USER.IS_ACTIVE.isTrue())
                .fetch();
    }

    public Optional<User> getUserByNo(Long userNo) {
        return query.select()
                .from(USER)
                .where(USER.USERNO
                .eq(userNo))
                .fetchOptionalInto(User.class);
    }
}
