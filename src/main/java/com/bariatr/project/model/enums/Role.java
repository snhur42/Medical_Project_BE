package com.bariatr.project.model.enums;


import java.util.HashSet;
import java.util.Set;

public enum Role {
    ADMIN,
    DOCTOR,
    PATIENT;

//    public Set<SimpleGrantedAuthority> getAuthorities() {
//        Set<SimpleGrantedAuthority> permissions = new HashSet<>();
//        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//        return permissions;
//    }
}