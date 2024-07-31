package org.uvt.uvtgaseste.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Setter @Getter
public class CustomUser implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String oauth2provider;
    private String oauth2providerId;
    private Set<GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    public CustomUser (String username, String password, String oauth2provider, String oauth2providerId) {
        this.username = username;
        this.password = password;
        this.oauth2provider = oauth2provider;
        this.oauth2providerId = oauth2providerId;
        this.authorities = new HashSet<>();
    }
}
