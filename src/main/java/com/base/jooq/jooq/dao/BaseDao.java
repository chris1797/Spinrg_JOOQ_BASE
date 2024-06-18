package com.base.jooq.jooq.dao;

import lombok.extern.slf4j.Slf4j;
import org.jooq.Field;
import org.jooq.Record;

import static com.base.jooq.jooq.bean.TestDb.TEST_DB;

@Slf4j
public class BaseDao {

    protected com.base.jooq.jooq.bean.TestDb testDb = TEST_DB;

    protected <T extends Record> T getConvertRecord(T record) {
        for (Field<?> field : record.fields()) {
            if (record.getValue(field) == null)
                record.changed(field, false);
        }
        return record;
    }
}
