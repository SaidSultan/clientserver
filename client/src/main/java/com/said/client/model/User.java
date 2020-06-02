package com.said.client.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
public class User implements UserDetails {
    private long id;
    private String login;
    private String email;
    private String name;
    private String password;
    private Set<Role> roles;

    public User() {
    }

    public User(long id, String login, String email, String name, String password, Set<Role> roles) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }
    public User( String login, String email, String name, String password, Set<Role> roles) {
        this.login = login;
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
