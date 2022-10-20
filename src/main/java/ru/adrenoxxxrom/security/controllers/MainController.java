package ru.adrenoxxxrom.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private static final String INDEX_PAGE = "index";

    @GetMapping(value = "/")
    public String getUserPage() {
        return INDEX_PAGE;
    }
}
