package com.foi.userservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController
{
    private static final String REDIRECT = "redirect";
    private static final String COLON = ":";
    private static final String URL_INDEX = "/";
    private static final String URL_LOGIN = "/login";

    @RequestMapping(value = URL_INDEX, method = RequestMethod.GET)
    private String index()
    {
        return REDIRECT + COLON + URL_LOGIN;
    }
}
