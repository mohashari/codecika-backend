package com.codecika.usermanagement.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/**
 * Created by aska on 7/27/17.
 */
public class PasswordGenerator {
    private static final Logger log = LoggerFactory.getLogger(PasswordGenerator.class);

    public static String generate(Integer size) {
        StringBuilder generatedToken = new StringBuilder();
        try {
            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
            for (int i = 0; i < size; i++) {
                generatedToken.append(number.nextInt (9));
            }
        } catch (NoSuchAlgorithmException e) {
            log.error("ERROR: ", e);
        }

        return generatedToken.toString();
    }
}
