package com.codecika.usermanagement.enums;

/**
 * Created by yukibuwana on 3/31/17.
 */
public enum Role implements BaseModelEnum<String> {
   M("member"),
    A("admin"),
    T("trainer");

    private String internalValue;

    Role(String internalValue) {
        this.internalValue = internalValue;
    }

    @Override
    public String getInternalValue() {
        return internalValue;
    }
}
