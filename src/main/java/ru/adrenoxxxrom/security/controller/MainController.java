package ru.adrenoxxxrom.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {
    private static final String REDIRECT_TO_LOGIN_PAGE = "redirect:/login";

    @GetMapping(value = "/")
    public String getRedirectToUserPage(Principal principal) {
        return REDIRECT_TO_LOGIN_PAGE;
    }
}
