package com.said.client.transfer;



import com.said.client.model.Role;
import com.said.client.model.User;

import java.util.Set;

public class UserDTO {
    private long id;
    private String name;
    private String email;
    private Set<Role> roles;

    public UserDTO(long id, String name, String email, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public static UserDTO from(User user){
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRoles());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
