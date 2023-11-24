package com.base.jooq.jooq.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardDao extends BaseDao {

    private final DSLContext query;

    private Condition isIncludes(String keyword) {
        if (Objects.isNull(keyword)) return DSL.condition(true);
        return tables.TBLBOARD.TITLE.contains(keyword)
                .or(tables.TBLBOARD.CONTENTS.contains(keyword));
    }
}
