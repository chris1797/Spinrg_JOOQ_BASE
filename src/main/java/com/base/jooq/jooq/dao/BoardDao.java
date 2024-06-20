package com.base.jooq.jooq.dao;

import com.base.jooq.jooq.bean.tables.pojos.Board;
import com.base.jooq.jooq.bean.tables.records.BoardRecord;
import com.base.jooq.jooq.dto.reference.BoardDto;
import com.base.jooq.jooq.dto.reference.CommentDto;
import com.base.jooq.jooq.dto.request.board.BoardPageReq;
import com.base.jooq.jooq.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.base.jooq.jooq.bean.Tables.BOARD;


@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardDao extends BaseDao {

    private final com.base.jooq.jooq.bean.tables.Board board = BOARD;
    private final DSLContext query;

    private Condition isIncludes(BoardPageReq req) {
        if (Objects.isNull(req.getKeyword())) return DSL.condition(true);

        return board.TITLE.contains(req.getKeyword())
                .or(board.CONTENT.contains(req.getKeyword()));
    }

    public List<BoardResponse> getAllBoard(BoardPageReq req) {
        return query.select()
                .from(board)
                .where(isIncludes(req))
                .fetch().into(BoardResponse.class);
    }

    public Optional<Board> getBoardByNo(Long boardNo) {
        return query.select()
                .from(board)
                .where(board.BOARD_NO.eq(boardNo))
                .fetchOptionalInto(Board.class);
    }

    public Boolean save(BoardRecord record) {
        return query.insertInto(board)
                .set(this.getConvertRecord(record))
                .execute() > 0;
    }

    public Boolean remove(Long boardNo) {
        return query.deleteFrom(testDb.BOARD)
                .where(board.BOARD_NO.eq((boardNo)))
                .execute() > 0;
    }

    public CommentDto getCommentByBoardNo(Long boardNo) {
        return query.select()
                .from(testDb.COMMENT)
                .where(testDb.COMMENT.BOARD_NO.eq(boardNo))
                .fetchOneInto(CommentDto.class);
    }
}
