package com.base.jooq.jooq.dto.response;

import com.base.jooq.jooq.dto.reference.BoardDto;
import com.base.jooq.jooq.dto.reference.CommentDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BoardResponse extends BoardDto {

    private CommentDto comment;
}
