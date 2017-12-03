package com.codecika.usermanagement.enums;

/**
 * Created by yukibuwana on 3/31/17.
 */
public enum Gender implements BaseModelEnum<String> {
    F("Female"),
    M("Male"),
    N("None");

    private String internalValue;

    Gender(String internalValue) {
        this.internalValue = internalValue;
    }

    @Override
    public String getInternalValue() {
        return internalValue;
    }
}
