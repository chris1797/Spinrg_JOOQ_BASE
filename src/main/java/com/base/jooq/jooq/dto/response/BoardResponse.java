package com.base.jooq.jooq.dto.response;

import com.base.jooq.jooq.dto.reference.CommentDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jooq.generated.tables.pojos.Board;

@EqualsAndHashCode(callSuper = true)
@Data
public class BoardResponse extends Board {

    private CommentDto comment;
}
