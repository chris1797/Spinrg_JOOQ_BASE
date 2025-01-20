package com.base.jooq.jooq.dao;

import org.jooq.Field;
import org.jooq.Record;


public class BaseDao {

    protected <T extends Record> T getConvertRecord(T record) {
        for (Field<?> field : record.fields()) {
            if (record.getValue(field) == null)
                record.changed(field, false);
        }
        return record;
    }
}
