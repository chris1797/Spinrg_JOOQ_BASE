package com.base.jooq.service;

import com.base.jooq.jooq.bean.tables.pojos.Board;
import com.base.jooq.jooq.bean.tables.records.BoardRecord;
import com.base.jooq.jooq.dao.BoardDao;
import com.base.jooq.jooq.dto.request.BoardPageReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BoardService extends BaseService {

    private final BoardDao dao;
    private final ModelMapper mapper;

    public List<Board> getAllBoard(BoardPageReq req) {
        /**
         * Result<?> 타입으로 리턴되었을 경우 Tblboard 클래스타입으로 변환
         */
//        return dao.getAllBoard(req).into(Tblboard.class);
        return dao.getAllBoard(req);
    }

    public Board getBoard(Long boardNo) throws Exception{
        return dao.getBoardByNo(boardNo).orElseThrow(() ->
                new Exception("This data could not be found."));
    }

    public boolean save(Board entity) throws Exception {
        if (Objects.isNull(entity)) throw new Exception("This data is null.");
        BoardRecord record = mapper.map(entity, BoardRecord.class);
        return dao.save(record);
    }

    public boolean remove(Long boardNo) throws Exception {
        if (Objects.isNull(boardNo) || boardNo < 1) throw new Exception("This data could not be found.");
        return dao.remove(boardNo);
    }
}
