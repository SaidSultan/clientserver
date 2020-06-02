package com.said.client.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;


@Data
public class Role implements GrantedAuthority {
    private long id;
    private String name;

    public Role() {
    }

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
