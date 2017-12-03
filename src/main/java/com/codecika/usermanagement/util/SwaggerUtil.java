package com.codecika.usermanagement.util;


import com.google.common.base.Predicate;

/**
 * Created by rizkimuhammad on 3/31/17.
 */
public class SwaggerUtil {

    public static Predicate<String> excludePath(final String path) {
        return new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return !input.contains(path);
            }
        };
    }

    public static Predicate<String> includePath(final String path) {
        return new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.contains(path);
            }
        };
    }
}
