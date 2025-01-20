package com.base.jooq.jooq.dto.response;

import com.base.jooq.jooq.dto.reference.CommentDto;
import lombok.Data;

@Data
public class BoardResponse {

    private CommentDto comment;
}
