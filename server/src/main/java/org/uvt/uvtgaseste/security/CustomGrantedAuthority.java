package org.uvt.uvtgaseste.security;

import org.springframework.security.core.GrantedAuthority;
import org.uvt.uvtgaseste.models.Role;

public class CustomGrantedAuthority implements GrantedAuthority {
    private String authority;
    @Override
    public String getAuthority() {
        return null;
    }
    public CustomGrantedAuthority (Role role) {
        this.authority = role.name();
    }
}
