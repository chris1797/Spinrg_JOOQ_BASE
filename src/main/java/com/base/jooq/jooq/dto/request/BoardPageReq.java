package com.base.jooq.jooq.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BoardPageReq {

    private String keyword;
    private LocalDate startDate;
    private LocalDate endDate;
}
