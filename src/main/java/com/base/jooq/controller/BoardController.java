package com.base.jooq.controller;

import com.base.jooq.jooq.bean.tables.pojos.Board;
import com.base.jooq.jooq.dto.request.BoardPageReq;
import com.base.jooq.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService service;

    @GetMapping
    public ResponseEntity<List<Board>> getAllBoard(@ModelAttribute BoardPageReq req) {
        return ResponseEntity.ok().body(service.getAllBoard(req));
    }
}
