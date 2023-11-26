package com.base.jooq.jooq.dao;

import com.base.jooq.jooq.bean.Tables;
import com.base.jooq.jooq.bean.tables.pojos.Tblboard;
import com.base.jooq.jooq.bean.tables.records.TblboardRecord;
import com.base.jooq.jooq.dto.request.BoardPageReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public List<Tblboard> getAllBoard(BoardPageReq req) {
        return query.select()
                .from(Tables.TBLBOARD)
                .where(isIncludes(req))
                .fetch().into(Tblboard.class);
    }

    public Optional<Tblboard> getBoardByNo(Long boardNo) {
        return query.select()
                .from(Tables.TBLBOARD)
                .where(Tables.TBLBOARD.BOARDNO.eq(boardNo))
                .fetchOptionalInto(Tblboard.class);
    }

    public Boolean save(TblboardRecord record) {
        return query.insertInto(Tables.TBLBOARD)
                .set(this.getConvertRecord(record))
                .execute() > 0;
    }

    public Boolean remove(Long boardNo) {
        return query.deleteFrom(Tables.TBLBOARD)
                .where(Tables.TBLBOARD.BOARDNO.eq((boardNo)))
                .execute() > 0;
    }

}
