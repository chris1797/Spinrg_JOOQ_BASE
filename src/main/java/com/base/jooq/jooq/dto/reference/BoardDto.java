package com.base.jooq.jooq.dto.reference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BoardDto implements Serializable {

    private Long boardNo;
    private Long userNo;
    private String title;
    private String content;
}