package ru.adrenoxxxrom.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.adrenoxxxrom.security.model.Role;
import ru.adrenoxxxrom.security.model.User;
import ru.adrenoxxxrom.security.service.RoleService;
import ru.adrenoxxxrom.security.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbInit {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DbInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initDbUsers() {
        Set<Role> userRole = new HashSet<>();
        Set<Role> adminRole = new HashSet<>();
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");
        roleService.saveRole(roleUser);
        roleService.saveRole(roleAdmin);
        userRole.add(roleUser);
        adminRole.add(roleUser);
        adminRole.add(roleAdmin);

        User admin = new User();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setFirstName("Евгений");
        admin.setLastName("Казьмин");
        admin.setAge((byte) 30);
        admin.setRoles(adminRole);
        userService.saveUser(admin);

        User user = new User();
        user.setId(2L);
        user.setUsername("user");
        user.setPassword("user");
        user.setFirstName("Василий");
        user.setLastName("Обухов");
        user.setAge((byte) 20);
        user.setRoles(userRole);
        userService.saveUser(user);
    }
}
