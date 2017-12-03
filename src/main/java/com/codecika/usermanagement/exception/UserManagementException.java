package com.codecika.usermanagement.exception;

import com.codecika.usermanagement.enums.StatusCode;
import lombok.Data;

/**
 * Created by yukibuwana on 1/24/17.
 */

@Data
public class UserManagementException extends RuntimeException {

    private StatusCode code = StatusCode.ERROR;

    public UserManagementException() {
        super();
    }

    public UserManagementException(String message) {
        super(message);
    }

    public UserManagementException(String message, StatusCode code) {
        super(message);
        this.code = code;
    }
}
