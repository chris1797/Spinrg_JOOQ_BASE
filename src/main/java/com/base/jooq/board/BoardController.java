package com.base.jooq.board;

import com.base.jooq.jooq.dto.request.board.BoardPageReq;
import com.base.jooq.jooq.dto.request.board.BoardSaveReq;
import com.base.jooq.jooq.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.jooq.generated.tables.pojos.Board;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService service;


    @GetMapping
    public ResponseEntity<List<BoardResponse>> getAllBoard(@ModelAttribute BoardPageReq req) {
        return ResponseEntity.ok().body(service.getAllBoard(req));
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<Board> getBoard(@PathVariable Long boardNo) throws Exception {
        return ResponseEntity.ok().body(service.getBoard(boardNo));
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@ModelAttribute BoardSaveReq req) throws Exception {
        return ResponseEntity.ok().body(service.save(req));
    }

    @DeleteMapping(value = "/{boardNo}")
    public ResponseEntity<Boolean> remove(@PathVariable Long boardNo) throws Exception {
        return ResponseEntity.ok().body(service.remove(boardNo));
    }
}
