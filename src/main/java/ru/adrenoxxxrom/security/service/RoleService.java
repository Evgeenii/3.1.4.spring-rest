package ru.adrenoxxxrom.security.service;

import ru.adrenoxxxrom.security.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();
    void saveRole(Role role);
}
