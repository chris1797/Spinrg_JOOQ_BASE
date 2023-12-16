package com.base.jooq.jooq.dto.request.user;

import com.base.jooq.jooq.bean.tables.pojos.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageReq extends User {
}
