package com.codecika.usermanagement.validator;

import com.codecika.usermanagement.exception.UserManagementException;
import com.codecika.usermanagement.persistence.domain.User;
import com.codecika.usermanagement.persistence.repository.UserRepository;
import com.codecika.usermanagement.util.Constants;
import com.codecika.usermanagement.util.ValidationUtil;
import com.codecika.usermanagement.vo.*;
import com.codecika.usermanagement.enums.Role;
import com.nostratech.cariparkir.usermanagement.vo.*;
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
    ConsumerRepository consumerRepository;

    @Autowired
    MitraRepository mitraRepository;

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

    public String validateAdd(User user, String username) {
        //set message
        String validateUsernameEmpty = messageSource.getMessage("validate.username.empty", null,
                LocaleContextHolder.getLocale());
        String validateFisrtNameEmpty = messageSource.getMessage("validate.firtsname.empty", null,
                LocaleContextHolder.getLocale());
        String validateLastNameEmpty = messageSource.getMessage("validate.lastname.empty", null,
                LocaleContextHolder.getLocale());
        String validateUserRuleEmpty = messageSource.getMessage("validate.user.rule.empty", null,
                LocaleContextHolder.getLocale());
        String validateUserDuplicate = messageSource.getMessage("validate.user.duplicate", null,
                LocaleContextHolder.getLocale());
        String validateEmailInvalid = messageSource.getMessage("validate.email.invalid", null,
                LocaleContextHolder.getLocale());
        String validatePhoneNumber = messageSource.getMessage("validate.phone.number.letter", null,
                LocaleContextHolder.getLocale());
        String validateFirstNameMax = messageSource.getMessage("validate.firstname.max", null,
                LocaleContextHolder.getLocale());
        String validateLastNameMax = messageSource.getMessage("validate.lastname.max", null,
                LocaleContextHolder.getLocale());
        String validateAdminCantAdd = messageSource.getMessage("validate.admin.not.add", null,
                LocaleContextHolder.getLocale());

        if (StringUtils.isEmpty(user.getRole())) {
            return validateUserRuleEmpty;
        }


        User userNow = userRepository.findByUsernameIgnoreCase(username);
        if (!userNow.getRole().equals(Role.Z)) {
            if (user.getStatusAdmin().equals("A")) {
                return validateAdminCantAdd;
            }
        }

        if (StringUtils.isEmpty(user.getUsername())) {
            return validateUsernameEmpty;
        }

        if (StringUtils.isEmpty(user.getFirstName())) {
            return validateFisrtNameEmpty;
        }

        if (StringUtils.isEmpty(user.getLastName())) {
            return validateLastNameEmpty;
        }

        User duplicate = userRepository.findByUsernameIgnoreCase(user.getUsername());
        if (duplicate != null) {
            return validateUserDuplicate;
        }

        if (!EmailValidator.getInstance().isValid(user.getUsername())) {
            return validateEmailInvalid;
        }

        if (!ValidationUtil.regexValidate(user.getPhone(), ValidationUtil.NUMBER_PATTERN)) {
            return validatePhoneNumber;
        }

        if (ValidationUtil.isNotEmptyOrNull(user.getFirstName()) && user.getFirstName().length() > 50) {
            return validateFirstNameMax;
        }

        if (ValidationUtil.isNotEmptyOrNull(user.getLastName()) && user.getLastName().length() > 50) {
            return validateLastNameMax;
        }

        return null;
    }

    public String validateEdit(UserVO userVO, String username, User user) {
        //set message
        String validateUserNotUpdate = messageSource.getMessage("validate.user.not.update", null,
                LocaleContextHolder.getLocale());
        String validateRoleNotUpdate = messageSource.getMessage("validate.role.not.update", null,
                LocaleContextHolder.getLocale());
        String validateStatusNotUpdate = messageSource.getMessage("validate.status.not.update", null,
                LocaleContextHolder.getLocale());
        String validateFirstNameMax = messageSource.getMessage("validate.firstname.max", null,
                LocaleContextHolder.getLocale());
        String validateLastNameMax = messageSource.getMessage("validate.lastname.max", null,
                LocaleContextHolder.getLocale());
        String validateAdminCantUpdate = messageSource.getMessage("validate.admin.not.update", null,
                LocaleContextHolder.getLocale());

        User userNow = userRepository.findByUsernameIgnoreCase(username);

        if (!StringUtils.isEmpty(userVO.getUsername())) {
            return validateUserNotUpdate;
        }

//        if (!StringUtils.isEmpty(userVO.getRole())) {
//            return validateRoleNotUpdate;
//        }

        if (userNow.getRole().equals(Role.A)) {
            if (!userNow.getRole().equals(Role.Z)) {
                if (user.getStatusAdmin().equals("A")) {
                    return validateAdminCantUpdate;
                }
            }
        }

        if (userVO.getEnable() != null) {
            return validateStatusNotUpdate;
        }

        if (ValidationUtil.isNotEmptyOrNull(userVO.getFirstName()) && userVO.getFirstName().length() > 50) {
            return validateFirstNameMax;
        }

        if (ValidationUtil.isNotEmptyOrNull(userVO.getLastName()) && userVO.getLastName().length() > 50) {
            return validateLastNameMax;
        }

        return null;
    }

    public String validateDisabled(String secureId, String username) {

        //set message
        String validateSecureIdEmpty = messageSource.getMessage("validate.secureid.empty", null,
                LocaleContextHolder.getLocale());
        String validateUserNotFound = messageSource.getMessage("validate.username.not.found", null,
                LocaleContextHolder.getLocale());
        String validateUserDisable = messageSource.getMessage("validate.status.already.disable", null,
                LocaleContextHolder.getLocale());
        String validateAdminCantDisable = messageSource.getMessage("validate.admin.not.disable", null,
                LocaleContextHolder.getLocale());

        if (StringUtils.isEmpty(secureId)) {
            return validateSecureIdEmpty;
        }

        User user = userRepository.findBySecureId(secureId);
        if (user == null) {
            return validateUserNotFound;
        }
        User userNow = userRepository.findByUsernameIgnoreCase(username);
        if (!userNow.getRole().equals(Role.Z)) {
            if (user.getStatusAdmin().equals("A")) {
                return validateAdminCantDisable;
            }
        }

        if (!user.getEnable()) {
            return validateUserDisable;
        }

        return null;
    }

    public String checkRole(String role) {

        //set message
        String validateRoleNotFind = messageSource.getMessage("validate.role.not.find", null,
                LocaleContextHolder.getLocale());

        Role find = Role.valueOf(role);
        if (find == null) {
            return validateRoleNotFind;
        }

        return null;
    }

    public String validateUpdatePassword(PasswordVO passwordVO, String username) {

        //set validate messages
        String passwordValidate = messageSource.getMessage("validate.password.update", null,
                LocaleContextHolder.getLocale());
        String validateUsernameWrong = messageSource.getMessage("error.username.wrong", null,
                LocaleContextHolder.getLocale());
        String errorKeycloakServer = messageSource.getMessage("error.keycloack.server", null,
                LocaleContextHolder.getLocale());


        // check format
        if (!ValidationUtil.regexValidate(passwordVO.getNewPassword(), ValidationUtil.PASSWORD_PATTERN)) {
            return passwordValidate;
        }

        // check username
        User user = userRepository.findByUsernameIgnoreCase(username);
        if (user == null) return validateUsernameWrong;


        return null;
    }

    public String validateUpdatePasswordUser(PasswordUserVO passwordVO, String username) {

        //set validate messages
        String passwordValidate = messageSource.getMessage("validate.password.update", null,
                LocaleContextHolder.getLocale());
        String validateUsernameWrong = messageSource.getMessage("error.username.wrong", null,
                LocaleContextHolder.getLocale());
        String validateAdminCantChangePassword = messageSource.getMessage("validate.admin.not.update.password", null,
                LocaleContextHolder.getLocale());

        // check format
        if (!ValidationUtil.regexValidate(passwordVO.getPassword(), ValidationUtil.PASSWORD_PATTERN)) {
            return passwordValidate;
        }

        // check username
        User user = userRepository.findBySecureId(passwordVO.getSecureId());
        if (user == null) return validateUsernameWrong;

        User userNow = userRepository.findByUsernameIgnoreCase(username);
        if (!userNow.getRole().equals(Role.Z)||!userNow.getRole().equals(Role.A)||!userNow.getRole().equals(Role.U)) {
            if (user.getStatusAdmin().equals("A")) {
                return validateAdminCantChangePassword;
            }
        }

        return null;
    }

    public String validateUpdatePasswordMitra(PasswordUserVO passwordVO) {

        //set validate messages
        String passwordValidate = messageSource.getMessage("validate.password.update", null,
                LocaleContextHolder.getLocale());
        String validateUsernameWrong = messageSource.getMessage("error.username.wrong", null,
                LocaleContextHolder.getLocale());

        // check format
        if (!ValidationUtil.regexValidate(passwordVO.getPassword(), ValidationUtil.PASSWORD_PATTERN)) {
            return passwordValidate;
        }

        // check username
        Mitra mitra = mitraRepository.findBySecureId(passwordVO.getSecureId());
        if (mitra == null) return validateUsernameWrong;

        return null;
    }


    public String validateEnabled(String secureId, String username) {
        //set message
        String validateSecureIdEmpty = messageSource.getMessage("validate.secureid.empty", null,
                LocaleContextHolder.getLocale());
        String validateUserNotFound = messageSource.getMessage("validate.username.not.found", null,
                LocaleContextHolder.getLocale());
        String validateStatusEnable = messageSource.getMessage("validate.status.already.enable", null,
                LocaleContextHolder.getLocale());
        String validateAdminCantEnable = messageSource.getMessage("validate.admin.not.enable", null,
                LocaleContextHolder.getLocale());

        if (StringUtils.isEmpty(secureId)) {
            return validateSecureIdEmpty;
        }

        User user = userRepository.findBySecureId(secureId);
        if (user == null) {
            return validateUserNotFound;
        }
        User userNow = userRepository.findByUsernameIgnoreCase(username);
        if (!userNow.getRole().equals(Role.Z)||!userNow.getRole().equals(Role.A)||!userNow.getRole().equals(Role.U)) {
            if (user.getStatusAdmin().equals("A")) {
                return validateAdminCantEnable;
            }
        }
//        User userNow = userRepository.findByUsernameIgnoreCase(username);
//        if (userNow.getRole().equals(Role.A)) {
//            if (user.getRole().equals(Role.A) || user.getRole().equals(Role.Z)) {
//                return validateAdminCantEnable;
//            }
//        }

        if (user.getEnable()) {
            return validateStatusEnable;
        }

        return null;
    }

    public String validateConsumerEnabled(String secureId) {
        //set message
        String validateSecureIdEmpty = messageSource.getMessage("validate.secureid.empty", null,
                LocaleContextHolder.getLocale());
        String validateUserNotFound = messageSource.getMessage("validate.username.not.found", null,
                LocaleContextHolder.getLocale());
        String validateStatusEnable = messageSource.getMessage("validate.status.already.enable", null,
                LocaleContextHolder.getLocale());

        if (StringUtils.isEmpty(secureId)) {
            return validateSecureIdEmpty;
        }

        Consumer consumer = consumerRepository.findBySecureId(secureId);

        if (ValidationUtil.isEmptyOrNull(consumer)) {
            return validateUserNotFound;
        }

        User user = consumer.getUser();

        if (user.getEnable()) {
            return validateStatusEnable;
        }

        return null;
    }

    public String validateConsumerDisable(String secureId) {
        //set message
        String validateSecureIdEmpty = messageSource.getMessage("validate.secureid.empty", null,
                LocaleContextHolder.getLocale());
        String validateUserNotFound = messageSource.getMessage("validate.username.not.found", null,
                LocaleContextHolder.getLocale());
        String validateUserDisable = messageSource.getMessage("validate.status.already.disable", null,
                LocaleContextHolder.getLocale());

        if (StringUtils.isEmpty(secureId)) {
            return validateSecureIdEmpty;
        }

        Consumer consumer = consumerRepository.findBySecureId(secureId);

        if (ValidationUtil.isEmptyOrNull(consumer)) {
            return validateUserNotFound;
        }

        User user = consumer.getUser();

        if (!user.getEnable()) {
            return validateUserDisable;
        }

        return null;
    }

    public String validateMitraEnabled(String secureId) {
        //set message
        String validateSecureIdEmpty = messageSource.getMessage("validate.secureid.empty", null,
                LocaleContextHolder.getLocale());
        String validateUserNotFound = messageSource.getMessage("validate.username.not.found", null,
                LocaleContextHolder.getLocale());
        String validateStatusEnable = messageSource.getMessage("validate.status.already.enable", null,
                LocaleContextHolder.getLocale());

        if (StringUtils.isEmpty(secureId)) {
            return validateSecureIdEmpty;
        }

        Mitra mitra = mitraRepository.findBySecureId(secureId);

        if (ValidationUtil.isEmptyOrNull(mitra)) {
            return validateUserNotFound;
        }

        User user = mitra.getUser();

        if (user.getEnable()) {
            return validateStatusEnable;
        }

        return null;
    }

    public String validateMitraDisable(String secureId) {
        //set message
        String validateSecureIdEmpty = messageSource.getMessage("validate.secureid.empty", null,
                LocaleContextHolder.getLocale());
        String validateUserNotFound = messageSource.getMessage("validate.username.not.found", null,
                LocaleContextHolder.getLocale());
        String validateUserDisable = messageSource.getMessage("validate.status.already.disable", null,
                LocaleContextHolder.getLocale());

        if (StringUtils.isEmpty(secureId)) {
            return validateSecureIdEmpty;
        }

        Mitra mitra = mitraRepository.findBySecureId(secureId);

        if (ValidationUtil.isEmptyOrNull(mitra)) {
            return validateUserNotFound;
        }

        User user = mitra.getUser();

        if (!user.getEnable()) {
            return validateUserDisable;
        }

        return null;
    }

    public String validateForgotPassword(String email) {

        //set validate message
        String validateUsernameWrong = messageSource.getMessage("error.username.wrong", null,
                LocaleContextHolder.getLocale());

        // check username
        User user = userRepository.findByUsernameIgnoreCase(email);
        if (user == null) return validateUsernameWrong;
        return null;
    }

    public String validateEmailLandingpage(EmailVO vo) {

        //set messagae
        String validateEmail = messageSource.getMessage("validate.email.empty", null,
                LocaleContextHolder.getLocale());
        String validateName = messageSource.getMessage("validate.name.empty", null,
                LocaleContextHolder.getLocale());
        String validateMessage = messageSource.getMessage("validate.message.empty", null,
                LocaleContextHolder.getLocale());

        if (StringUtils.isEmpty(vo.getEmail())) {
            return validateEmail;
        }

        if (StringUtils.isEmpty(vo.getName())) {
            return validateName;
        }

        if (StringUtils.isEmpty(vo.getMessage())) {
            return validateMessage;
        }

        return null;
    }

    public String validateGetUserByQRCode(String qrCode) {

        //set message
        String validateQRCodeEmpty = messageSource.getMessage("validate.qrcode.empty", null,
                LocaleContextHolder.getLocale());
        String validateConsumerNotFound = messageSource.getMessage("validate.consumer.not.found", null,
                LocaleContextHolder.getLocale());
        String validateConsumerDisable = messageSource.getMessage("validate.consumer.disable", null,
                LocaleContextHolder.getLocale());


        if (StringUtils.isEmpty(qrCode)) {
            return validateQRCodeEmpty;
        }

        Consumer consumer = consumerRepository.findByQrCode(qrCode);

        if (consumer == null) return validateConsumerNotFound;

        if (!consumer.getUser().getEnable()) return validateConsumerDisable;

        return null;
    }

    public String validateAssignMitra(Mitra mitra) {
        String validateMitraNotFound = messageSource.getMessage("validate.mitra.not.found", null,
                LocaleContextHolder.getLocale());
        String validateMitraDisable = messageSource.getMessage("validate.mitra.disable", null,
                LocaleContextHolder.getLocale());
        String validateMitraAssigned = messageSource.getMessage("validate.mitra.assigned", null,
                LocaleContextHolder.getLocale());

        if (ValidationUtil.isEmptyOrNull(mitra)) {
            return validateMitraNotFound;
        }

        if (!mitra.getUser().getEnable()) return validateMitraDisable;


        return null;
    }

    public String validateUpdatePasswordConsumer(PasswordConsumerVO vo) {
        // set error message
        String usernameRequired = messageSource.getMessage("validate.username.required", null,
                LocaleContextHolder.getLocale());
        String passwordRequired = messageSource.getMessage("validate.password.required", null,
                LocaleContextHolder.getLocale());
        String errorKeycloakServer = messageSource.getMessage("error.keycloak.server", null,
                LocaleContextHolder.getLocale());
        String userNonActive = messageSource.getMessage("error.user.disable", null,
                LocaleContextHolder.getLocale());
        String userNotFound = messageSource.getMessage("error.user.not.found", null,
                LocaleContextHolder.getLocale());


        if (StringUtils.isEmpty(vo.getUsername())) {
            return usernameRequired;
        }

        if (StringUtils.isEmpty(vo.getOldPassword())) {
            return passwordRequired;
        }

        User find = userRepository.findByUsernameIgnoreCase(vo.getUsername());
        if (find == null) return userNotFound;

        // cek user in database
        if (!find.getEnable()) return userNonActive;

        // cek user in keycloak
        Keycloak keycloakAdmin = null;
        // connect to keycloak server
        try {
            keycloakAdmin = Keycloak.getInstance(keycloakServer, Constants.Keycloak.REALM_MASTER, usernameAdmin,
                    passwordAdmin, Constants.Keycloak.ADMIN_CLI_CLIENT_ID);
            UserRepresentation userRepresentation = keycloakAdmin.realm(realm).users().get(find.getKeycloakId()).toRepresentation();
            if (!userRepresentation.isEnabled()) return userNonActive;
        } catch (Exception e) {
            log.error("ERROR: ", e);
            throw new UserManagementException(errorKeycloakServer);
        }

        return null;
    }
}

