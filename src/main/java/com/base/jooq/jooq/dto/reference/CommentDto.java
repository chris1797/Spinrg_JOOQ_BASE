package com.base.jooq.jooq.dto.reference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.base.jooq.jooq.bean.tables.pojos.Comment}
 */
@Data
@NoArgsConstructor
public class CommentDto implements Serializable {
    private Long commentNo;
    private Long boardNo;
    private Long userNo;
    private String content;
}