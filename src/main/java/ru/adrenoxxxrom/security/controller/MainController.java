package ru.adrenoxxxrom.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {
    private static final String REDIRECT_TO_LOGIN_PAGE = "redirect:/login";
    private static final String REDIRECT_TO_ADMIN_PAGE = "redirect:/admin";
    private static final String REDIRECT_TO_USER_PAGE = "redirect:/user";

    @GetMapping(value = "/")
    public String getRedirectToUserPage(Principal principal) {
        if (principal == null) {
            return REDIRECT_TO_LOGIN_PAGE;
        } else if (principal.getName().equals("admin")) {
            return REDIRECT_TO_ADMIN_PAGE;
        }
        return REDIRECT_TO_USER_PAGE;
    }
}
