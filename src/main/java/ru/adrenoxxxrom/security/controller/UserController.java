package ru.adrenoxxxrom.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.adrenoxxxrom.security.service.UserService;

import java.security.Principal;

@Controller
public class UserController {
    private static final String USER_PAGE = "user/users-page";
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping(value = "/user")
    public String getUserPage(Model model, Principal principal) {
        Long id = userService.getUserByUsername(principal.getName()).getId();
        model.addAttribute("user", userService.getUserById(id));
        return USER_PAGE;
    }
}