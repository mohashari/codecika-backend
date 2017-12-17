package com.codecika.usermanagement.vo;

import com.codecika.usermanagement.enums.Gender;
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

    @JsonProperty("user_enable")
    Boolean userEnable;

    @JsonProperty("birthdate")
    private String birthDate;

    @JsonProperty("device_id")
    private String deviceId;

    private String gender;

    private String keycloakId;

    private String code;
}
