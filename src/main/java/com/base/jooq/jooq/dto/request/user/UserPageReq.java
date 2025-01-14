package com.base.jooq.jooq.dto.request.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jooq.generated.tables.pojos.User;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageReq extends User {
}
