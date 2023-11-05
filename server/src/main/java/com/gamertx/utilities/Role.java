package com.gamertx.utilities;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Role {
    CUSTOMER(Arrays.asList(Permission.READ_ALL_PRODUCTS)),

    ADMIN(Arrays.asList(Permission.SAVE_ONE_PRODUCT,Permission.READ_ALL_PRODUCTS)),
    ;

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
