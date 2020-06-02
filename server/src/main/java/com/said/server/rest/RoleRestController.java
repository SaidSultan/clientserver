package com.said.server.rest;

import com.said.server.model.Role;
import com.said.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/rest/role")
public class RoleRestController {
    private RoleService roleService;

    @Autowired
    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("all")
    public List<Role> roleList () {
        return roleService.getAllRoles();
    }
    @PostMapping("add")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        roleService.addRole(role);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
    @PutMapping("update")
    public ResponseEntity<Role> updateRole(@RequestBody Role role) {
        roleService.updateRole(role);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteRole(@RequestParam("id") long id) {
        roleService.deleteRoleById(id);
        return ResponseEntity.ok().build();
    }
}
