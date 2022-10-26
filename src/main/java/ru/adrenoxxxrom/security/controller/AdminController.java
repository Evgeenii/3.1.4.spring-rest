package ru.adrenoxxxrom.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.adrenoxxxrom.security.model.User;
import ru.adrenoxxxrom.security.service.RoleService;
import ru.adrenoxxxrom.security.service.UserService;

@Controller
public class AdminController {
    private static final String ADMIN_PAGE = "admin/admin-page";
    private static final String USER_CREATE_PAGE = "admin/user-create-page";
    private static final String USER_UPDATE_PAGE = "admin/user-update-page";
    private static  final String REDIRECT_TO_ADMIN_PAGE = "redirect:/admin";
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String getAdminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return ADMIN_PAGE;
    }

    @GetMapping(value = "/user-create")
    public String getUserFormForCreate(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getRoles());
        return USER_CREATE_PAGE;
    }

    @PostMapping("/touch-user")
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return REDIRECT_TO_ADMIN_PAGE;

    }

    @GetMapping("/user-update-form/{id}")
    public String getUserFormForUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getRoles());
        return USER_UPDATE_PAGE;
    }

    @PostMapping("/user-update/{id}")
    public String updateUser(Long id, @ModelAttribute("user") User user) {
        userService.updateUser(id, user);
        return REDIRECT_TO_ADMIN_PAGE;
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUserById(id);
        return REDIRECT_TO_ADMIN_PAGE;
    }
}
