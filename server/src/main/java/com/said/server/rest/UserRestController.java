package com.said.server.rest;

import com.said.server.model.Role;
import com.said.server.model.User;
import com.said.server.service.RoleService;
import com.said.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("admin/rest/user")
public class UserRestController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("all")
    public List<User> getAllUsers() {
        return userService.getListUsers();
    }

    @GetMapping("{id}")
    public User getOne(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

    @GetMapping("login")
    public User findByLogin(@RequestParam("login") String login) {
        return userService.getUserByLogin(login);
    }

    @PostMapping("add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        Set<Role> roles = new LinkedHashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(roleService.getRoleByName(role.getName()));
        }
        user.setRoles(roles);

        userService.add(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Set<Role> roles = new LinkedHashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(roleService.getRoleByName(role.getName()));
        }
        user.setRoles(roles);

        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity deteleUser(@RequestParam("id") long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
