package ru.adrenoxxxrom.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.adrenoxxxrom.security.model.User;
import ru.adrenoxxxrom.security.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserRESTController {
    private final UserService userService;

    @Autowired
    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User getUser(Principal principal) {
        Long id = userService.getUserByUsername(principal.getName()).getId();
        return userService.getUserById(id);
    }
}
