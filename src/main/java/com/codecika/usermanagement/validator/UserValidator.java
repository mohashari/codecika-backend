package com.codecika.usermanagement.validator;

import com.codecika.usermanagement.exception.UserManagementException;
import com.codecika.usermanagement.persistence.domain.User;
import com.codecika.usermanagement.persistence.repository.UserRepository;
import com.codecika.usermanagement.util.Constants;
import com.codecika.usermanagement.util.ValidationUtil;
import com.codecika.usermanagement.vo.*;
import com.codecika.usermanagement.enums.Role;
import org.apache.commons.validator.EmailValidator;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * Created by yukibuwana on 1/25/17.
 */

@Component
public class UserValidator {

    private static final Logger log = LoggerFactory.getLogger(UserValidator.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageSource messageSource;

    @Value("${keycloak.auth-server-url}")
    String keycloakServer;

    @Value("${keycloak.resource}")
    String clientId;

    @Value("${keycloak.realm}")
    String realm;

    @Value("${keycloak.credentials.secret}")
    String clientSecret;

    @Value("${username.admin.keycloak}")
    String usernameAdmin;

    @Value("${password.admin.keycloak}")
    String passwordAdmin;


}

