package com.base.jooq.board;

import com.base.jooq.jooq.dto.request.board.BoardPageReq;
import com.base.jooq.jooq.dto.request.board.BoardSaveReq;
import com.base.jooq.jooq.dto.response.BoardResponse;
import com.base.jooq.service.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.generated.tables.pojos.Board;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService extends BaseService {

    private final BoardDao boardDao;
    private final ModelMapper mapper;

    @Transactional(readOnly = true)
    public List<BoardResponse> getAllBoard(BoardPageReq req) {
        List<BoardResponse> list = boardDao.getAllBoardWithComment(req);
//        list.forEach(board -> board.setComment(boardDao.getCommentByBoardNo(board.getBoardNo())));

        return boardDao.getAllBoardWithComment(req);
    }

    @Transactional(readOnly = true)
    public Board getBoard(Long boardNo) throws Exception{
        return boardDao.getBoardByNo(boardNo).orElseThrow(() ->
                new Exception("This data could not be found."));
    }

    public boolean save(BoardSaveReq req) throws Exception {
        if (Objects.isNull(req)) throw new Exception("This data is null.");

        org.jooq.generated.tables.records.BoardRecord record = mapper.map(req, org.jooq.generated.tables.records.BoardRecord.class);
        return boardDao.save(record);
    }

    public boolean remove(Long boardNo) throws Exception {
        if (Objects.isNull(boardNo) || boardNo < 1) throw new Exception("This data could not be found.");
        return boardDao.remove(boardNo);
    }
}
