package com.codecika.usermanagement.util;

import com.codecika.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.util.StringUtils;

/**
 * Created by yukibuwana on 1/26/17.
 */

@Configuration
public class UsernameAuditorAware implements AuditorAware<String> {

    @Autowired
    UserService userService;

    @Override
    public String getCurrentAuditor() {
        if (StringUtils.isEmpty(userService.getUsername())) {
            return "system";
        } else {
            return userService.getUsername();
        }
    }
}
