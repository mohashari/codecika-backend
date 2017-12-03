package com.codecika.usermanagement.enums;

/**
 * Created by yukibuwana on 3/31/17.
 */
public enum Role implements BaseModelEnum<String> {
    A("admin"),
    S("surveyor"),
    C("consumer"),
    M("mitra"),
    Z("superadmin"),
    U("admin_usermanagement"),
    L("admin_location"),
    I("admin_info"),
    P("admin_promo"),
    D("admin_claim"),
    V("admin_view");

    private String internalValue;

    Role(String internalValue) {
        this.internalValue = internalValue;
    }

    @Override
    public String getInternalValue() {
        return internalValue;
    }
}
