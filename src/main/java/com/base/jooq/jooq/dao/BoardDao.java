package com.base.jooq.jooq.dao;

import com.base.jooq.jooq.dto.reference.CommentDto;
import com.base.jooq.jooq.dto.request.board.BoardPageReq;
import com.base.jooq.jooq.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.generated.TestDb;
import org.jooq.generated.tables.Board;
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

        return TestDb.TEST_DB.BOARD.TITLE.contains(req.getKeyword())
                .or(testDb.BOARD.CONTENTS.contains(req.getKeyword()));
    }

    public List<BoardResponse> getAllBoard(BoardPageReq req) {
        return query.select()
                .from(testDb.BOARD)
                .where(isIncludes(req))
                .fetch().into(BoardResponse.class);
    }

    public Optional<org.jooq.generated.tables.Board> getBoardByNo(Long boardNo) {
        return query.select()
                .from(testDb.BOARD)
                .where(testDb.BOARD.BOARDNO.eq(boardNo))
                .fetchOptionalInto(Board.class);
    }

    public Boolean save(org.jooq.generated.tables.records.BoardRecord record) {
        return query.insertInto(testDb.BOARD)
                .set(this.getConvertRecord(record))
                .execute() > 0;
    }

    public Boolean remove(Long boardNo) {
        return query.deleteFrom(testDb.BOARD)
                .where(testDb.BOARD.BOARDNO.eq((boardNo)))
                .execute() > 0;
    }

    public CommentDto getCommentByBoardNo(Long boardNo) {
        return query.select()
                .from(testDb.BOARD)
                .where(testDb.BOARD.BOARDNO.eq(boardNo))
                .fetchOneInto(CommentDto.class);
    }
}
