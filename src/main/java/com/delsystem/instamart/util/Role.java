package com.delsystem.instamart.util;

/**
 * DelSystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: Role.java
 * Date/time: 18 октябрь 2021 in 16:37
 * @author andreigp Andrei G. Pastushenko
 * @copy Can't use code
 */

public enum Role {
    COURIER("courier"),
    PICKER("picker"),
    SUPERVISOR("supervisor");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
