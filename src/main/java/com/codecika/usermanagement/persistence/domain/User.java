package com.codecika.usermanagement.persistence.domain;

/**
 * Created by dewiastuti on 2/14/17.
 */

import com.codecika.usermanagement.enums.Gender;
import com.codecika.usermanagement.enums.Role;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@Table(name = "USERS",
        indexes = {
                @Index(columnList = "USERNAME", name = "UK_USERNAME", unique = true)
        })
@DynamicUpdate
@Data
public class User extends Base {
    @Column(name = "KEYCLOAK_ID", nullable = false, length = 50)
    private String keycloakId;

    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;

    @Column(name = "FNAME", length = 50)
    private String firstName;

    @Column(name = "LNAME", length = 50)
    private String lastName;

    @Column(name = "PHONE", length = 20)
    private String phone;

    @Column(name = "ROLE", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "USERS_ENABLE", nullable = false)
    private Boolean userEnable;

    @Column(name = "BIRTHDATE")
    private String birthDate;

    @Column(name = "DEVICE_ID")
    private String deviceId;

    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "CODE")
    private String code;


    @PrePersist
    public void prePersist() {
        this.userEnable = true;
        this.code = RandomStringUtils.random(8,true,true);
        super.prePersist();
    }
}
