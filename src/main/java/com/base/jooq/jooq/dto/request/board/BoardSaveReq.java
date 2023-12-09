package com.base.jooq.jooq.dto.request.board;

import com.base.jooq.component.role.BoardType;
import com.base.jooq.jooq.bean.tables.pojos.Board;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class BoardSaveReq extends Board {

    private BoardType boardType;

}
