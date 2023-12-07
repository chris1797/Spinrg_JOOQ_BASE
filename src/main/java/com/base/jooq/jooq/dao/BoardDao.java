package com.base.jooq.jooq.dao;

import com.base.jooq.jooq.bean.Tables;
import com.base.jooq.jooq.bean.tables.pojos.Board;
import com.base.jooq.jooq.bean.tables.records.BoardRecord;
import com.base.jooq.jooq.dto.request.board.BoardPageReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
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

        return Tables.BOARD.TITLE.contains(req.getKeyword())
                .or(Tables.BOARD.CONTENTS.contains(req.getKeyword()));
    }

    public List<Board> getAllBoard(BoardPageReq req) {
        return query.select()
                .from(Tables.BOARD)
                .where(isIncludes(req))
                .fetch().into(Board.class);
    }

    public Optional<Board> getBoardByNo(Long boardNo) {
        return query.select()
                .from(Tables.BOARD)
                .where(Tables.BOARD.BOARDNO.eq(boardNo))
                .fetchOptionalInto(Board.class);
    }

    public Boolean save(BoardRecord record) {
        return query.insertInto(Tables.BOARD)
                .set(this.getConvertRecord(record))
                .execute() > 0;
    }

    public Boolean remove(Long boardNo) {
        return query.deleteFrom(Tables.BOARD)
                .where(Tables.BOARD.BOARDNO.eq((boardNo)))
                .execute() > 0;
    }

}
