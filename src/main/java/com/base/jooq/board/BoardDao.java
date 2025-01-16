package com.base.jooq.board;

import com.base.jooq.jooq.dao.BaseDao;
import com.base.jooq.jooq.dto.reference.CommentDto;
import com.base.jooq.jooq.dto.request.board.BoardPageReq;
import com.base.jooq.jooq.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.generated.tables.JBoard;
import org.jooq.generated.tables.JComment;
import org.jooq.generated.tables.pojos.Board;
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
    private final JBoard BOARD = JBoard.BOARD;

    private Condition isIncludes(BoardPageReq req) {
        if (Objects.isNull(req.getKeyword())) return DSL.condition(true);

        return BOARD.TITLE.contains(req.getKeyword())
                .or(BOARD.CONTENTS.contains(req.getKeyword()));
    }

    public List<BoardResponse> getAllBoardWithComment(BoardPageReq req) {
        JComment COMMENT = JComment.COMMENT;

        return query.select(BOARD.fields())
                .select(DSL.row(COMMENT.fields()))
                .from(BOARD)
                .leftJoin(COMMENT).on(BOARD.BOARDNO.eq(COMMENT.BOARDNO))
                .where(isIncludes(req))
                .fetch().into(BoardResponse.class);
    }

    public Optional<Board> getBoardByNo(Long boardNo) {
        return query.select()
                .from(BOARD)
                .where(BOARD.BOARDNO.eq(boardNo))
                .fetchOptionalInto(Board.class);
    }

    public Boolean save(org.jooq.generated.tables.records.BoardRecord record) {
        return query.insertInto(BOARD)
                .set(this.getConvertRecord(record))
                .execute() > 0;
    }

    public Boolean remove(Long boardNo) {
        return query.deleteFrom(BOARD)
                .where(BOARD.BOARDNO.eq((boardNo)))
                .execute() > 0;
    }

    public CommentDto getCommentByBoardNo(Long boardNo) {
        return query.select()
                .from(BOARD)
                .where(BOARD.BOARDNO.eq(boardNo))
                .fetchOneInto(CommentDto.class);
    }
}
