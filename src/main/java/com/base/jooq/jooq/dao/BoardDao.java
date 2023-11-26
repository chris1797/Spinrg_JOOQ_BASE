package com.base.jooq.jooq.dao;

import com.base.jooq.jooq.bean.Tables;
import com.base.jooq.jooq.bean.tables.pojos.Tblboard;
import com.base.jooq.jooq.dto.request.BoardPageReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardDao extends BaseDao {

    private final DSLContext query;

    private Condition isIncludes(BoardPageReq req) {
        if (Objects.isNull(req.getKeyword())) return DSL.condition(true);

        return Tables.TBLBOARD.TITLE.contains(req.getKeyword())
                .or(Tables.TBLBOARD.CONTENTS.contains(req.getKeyword()));
    }

    public List<Tblboard> getBoards(BoardPageReq req) {
        return query.select()
                .from(Tables.TBLBOARD)
                .where(isIncludes(req))
                .fetch().into(Tblboard.class);
    }

}
