package com.said.server.service;

import com.said.server.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    void addRole(Role role);
    Role getRoleById(long id);
    Role getRoleByName(String name);
    void updateRole(Role role);
    void deleteRoleById(long id);
}
