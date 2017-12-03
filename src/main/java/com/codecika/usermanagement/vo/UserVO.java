package com.codecika.usermanagement.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by aditrioka on 3/2/2017.
 */
@Data
public class UserVO extends BaseVO {

    String username;

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;

    String phone;
    String role;
    Boolean enable;

    @JsonProperty("astra_id")
    Long astraId;

    @JsonProperty("status_login")
    String statusLogin;

    @JsonProperty("status_admin")
    String statusAdmin;
}
