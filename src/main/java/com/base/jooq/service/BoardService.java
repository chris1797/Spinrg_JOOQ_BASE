package com.base.jooq.service;

import com.base.jooq.jooq.bean.tables.pojos.Tblboard;
import com.base.jooq.jooq.dao.BoardDao;
import com.base.jooq.jooq.dto.request.BoardPageReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BoardService {

    private final BoardDao dao;

    public List<Tblboard> getAllBoard(BoardPageReq req) {
        return dao.getAllBoard(req);
    }
}
