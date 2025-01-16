package com.base.jooq.web;

import lombok.Getter;
import org.jooq.generated.tables.JBoard;

import java.util.List;

@Getter
public class BoardWithCommentPagedResponse {

    private PagedResponse pageInfo;
    private List<JBoard> boardList;

}
