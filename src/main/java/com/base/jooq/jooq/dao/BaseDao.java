package com.base.jooq.jooq.dao;

import com.base.jooq.jooq.bean.Tables;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Field;
import org.jooq.Record;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
@RequiredArgsConstructor
public class BaseDao {

    protected Tables tables;

    protected <T extends Record> T getConvertRecord(T record) {
        for (Field<?> field : record.fields()) {
            if (record.getValue(field) == null)
                record.changed(field, false);
        }
        return record;
    }
}
