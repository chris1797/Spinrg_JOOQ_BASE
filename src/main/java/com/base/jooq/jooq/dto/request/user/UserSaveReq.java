package com.base.jooq.jooq.dto.request.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@JsonIgnoreProperties(value = {"userPwd"})
@Data
public class UserSaveReq {

    private String name;
    private String userId;
    private String userPwd;
    private String email;

}
